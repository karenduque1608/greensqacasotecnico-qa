package com.latam.generator.model;

/**
 * Representa una persona natural.
 */
public class Person extends User {

    public Person() {
        super();
    }

    public Person(String name,
                  String lastName,
                  int age,
                  String document,
                  String city,
                  String country,
                  String language) {

        super(
                name,
                lastName,
                age,
                document,
                city,
                country,
                language
        );
    }

    @Override
    public UserType getType() {
        return UserType.PERSON;
    }
}