package com.latam.generator.model;

/**
 * Representa una empresa.
 * El apellido siempre permanece vacío según el enunciado.
 */
public class Company extends User {

    public Company() {
        super();
        setLastName("");
    }

    public Company(String name,
                   int age,
                   String document,
                   String city,
                   String country,
                   String language) {

        super(
                name,
                "",
                age,
                document,
                city,
                country,
                language
        );
    }

    @Override
    public UserType getType() {
        return UserType.COMPANY;
    }
}