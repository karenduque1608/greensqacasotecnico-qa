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
     * Genera la cantidad de usuarios solicitada.
     */
    public List<User> generateUsers(int quantity) {

        List<User> users = new ArrayList<>();

        while (users.size() < quantity) {

            boolean isCompany = fakerUtil.isCompany();

            String name = fakerUtil.generateFirstName();

            String lastName = isCompany
                    ? ""
                    : fakerUtil.generateLastName();

            int age = fakerUtil.generateAge();

            String city = fakerUtil.generateCity();

            String country = fakerUtil.generateCountry();

            String language =
                    fakerUtil.generateLanguage(country);

            DocumentStrategy strategy;

            if (isCompany) {

                strategy = new CompanyDocumentStrategy();

            } else if (age < 18) {

                strategy = new MinorDocumentStrategy();

            } else {

                strategy = new AdultDocumentStrategy();

            }

            String document = strategy.generateDocument();

            String fullName = name + " " + lastName;

            if (!validationService.isValidAge(age)) {
                continue;
            }

            if (!validationService.isUniqueName(
                    fullName,
                    generatedNames)) {
                continue;
            }

            if (!validationService.isDocumentUnique(
                    document,
                    generatedDocuments)) {
                continue;
            }

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

            generatedNames.add(fullName);
            generatedDocuments.add(document);

            repository.save(user);

            users.add(user);

        }

        return users;

    }

}