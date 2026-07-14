package com.latam.generator.service;

import com.latam.generator.factory.UserFactory;
import com.latam.generator.model.User;
import com.latam.generator.repository.UserRepository;
import com.latam.generator.strategy.AdultDocumentStrategy;
import com.latam.generator.strategy.CompanyDocumentStrategy;
import com.latam.generator.strategy.DocumentStrategy;
import com.latam.generator.strategy.MinorDocumentStrategy;
import com.latam.generator.util.FakerUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Servicio encargado de generar usuarios
 * y almacenarlos en la base de datos.
 */
public class UserService {

    private final UserRepository repository;
    private final UserFactory factory;
    private final FakerUtil fakerUtil;
    private final ValidationService validationService;

    private final Set<String> generatedDocuments;
    private final Set<String> generatedNames;

    public UserService(UserRepository repository) {

        this.repository = repository;
        this.factory = new UserFactory();
        this.fakerUtil = new FakerUtil();
        this.validationService = new ValidationService();

        this.generatedDocuments = new HashSet<>();
        this.generatedNames = new HashSet<>();
    }

    /**
     * Genera la cantidad de usuarios solicitada de forma secuencial.
     */
    public List<User> generateUsers(int quantity) {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {

            User user = generateSingleValidUser();

            repository.save(user);

            users.add(user);

        }

        return users;

    }

    /**
     * Genera la cantidad de usuarios solicitada repartiendo el trabajo
     * entre varios hilos. El guardado en el repositorio se hace de forma
     * secuencial para evitar escrituras concurrentes sobre SQLite.
     */
    public List<User> generateUsersParallel(int quantity, int threadPoolSize) {

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        try {

            List<Callable<User>> tasks = new ArrayList<>();

            for (int i = 0; i < quantity; i++) {
                tasks.add(this::generateSingleValidUser);
            }

            List<Future<User>> futures = executor.invokeAll(tasks);

            List<User> users = new ArrayList<>();

            for (Future<User> future : futures) {

                User user = future.get();

                repository.save(user);

                users.add(user);

            }

            return users;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new RuntimeException("Generación paralela interrumpida.", e);

        } catch (ExecutionException e) {

            throw new RuntimeException("Error generando usuarios en paralelo.", e.getCause());

        } finally {

            executor.shutdown();

        }

    }

    /**
     * Genera un único usuario válido y único.
     * La generación de datos ficticios ocurre fuera de la sección
     * sincronizada para permitir que varios hilos trabajen en paralelo;
     * solo el registro de nombre/documento (estado compartido) se protege.
     */
    private User generateSingleValidUser() {

        while (true) {

            boolean isCompany = fakerUtil.isCompany();

            String name = fakerUtil.generateFirstName();

            String lastName = isCompany
                    ? ""
                    : fakerUtil.generateLastName();

            int age = fakerUtil.generateAge();

            if (!validationService.isValidAge(age)) {
                continue;
            }

            String city = fakerUtil.generateCity();

            String country = fakerUtil.generateCountry();

            String language = fakerUtil.generateLanguage(country);

            DocumentStrategy strategy = resolveStrategy(isCompany, age);

            String document = strategy.generateDocument();

            User user = factory.createUser(
                    isCompany,
                    name,
                    lastName,
                    age,
                    document,
                    city,
                    country,
                    language
            );

            if (!validationService.isValidLanguage(user)) {
                continue;
            }

            String fullName = name + " " + lastName;

            synchronized (this) {

                if (!validationService.isUniqueName(fullName, generatedNames)) {
                    continue;
                }

                if (!validationService.isDocumentUnique(document, generatedDocuments)) {
                    continue;
                }

                generatedNames.add(fullName);
                generatedDocuments.add(document);

                return user;

            }

        }

    }

    private DocumentStrategy resolveStrategy(boolean isCompany, int age) {

        if (isCompany) {
            return new CompanyDocumentStrategy();
        }

        if (age < 18) {
            return new MinorDocumentStrategy();
        }

        return new AdultDocumentStrategy();

    }

}