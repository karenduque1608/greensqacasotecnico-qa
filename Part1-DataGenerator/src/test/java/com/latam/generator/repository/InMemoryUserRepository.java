package com.latam.generator.repository;

import com.latam.generator.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria de {@link UserRepository} usada en las pruebas
 * unitarias para no depender de SQLite. Demuestra el beneficio del patrón
 * Repository combinado con el principio de inversión de dependencias (DIP).
 */
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User findByDocument(String document) {
        return users.stream()
                .filter(user -> user.getDocument().equals(document))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

}
