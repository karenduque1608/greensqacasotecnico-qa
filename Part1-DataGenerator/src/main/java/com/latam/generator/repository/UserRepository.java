package com.latam.generator.repository;

import com.latam.generator.model.User;

import java.util.List;

public interface UserRepository {

    /**
     * Guarda un usuario.
     */
    void save(User user);

    /**
     * Retorna todos los usuarios.
     */
    List<User> findAll();

    /**
     * Busca un usuario por documento.
     */
    User findByDocument(String document);

    /**
     * Elimina todos los registros.
     */
    void deleteAll();

}