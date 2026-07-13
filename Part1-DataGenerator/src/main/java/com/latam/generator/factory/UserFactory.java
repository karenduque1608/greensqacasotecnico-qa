package com.latam.generator.factory;

import com.latam.generator.model.Company;
import com.latam.generator.model.Person;
import com.latam.generator.model.User;

/**
 * Factory encargada de crear los distintos tipos de usuario.
 * Implementa el patrón Factory Method.
 */
public class UserFactory {

    /**
     * Crea un usuario según el tipo solicitado.
     *
     * @param isCompany true si es empresa
     * @return User
     */
    public User createUser(boolean isCompany,
                           String name,
                           String lastName,
                           int age,
                           String document,
                           String city,
                           String country,
                           String language) {

        if (isCompany) {

            return new Company(
                    name,
                    age,
                    document,
                    city,
                    country,
                    language
            );

        }

        return new Person(
                name,
                lastName,
                age,
                document,
                city,
                country,
                language
        );

    }

}