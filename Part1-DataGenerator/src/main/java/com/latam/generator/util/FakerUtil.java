package com.latam.generator.util;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

/**
 * Genera información ficticia para las pruebas.
 */
public class FakerUtil {

    private final Faker faker;
    private final Random random;

    public FakerUtil() {

        faker = new Faker(new Locale("es"));
        random = new Random();

    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public int generateAge() {

        return random.nextInt(
                Constants.MAX_AGE - Constants.MIN_AGE + 1
        ) + Constants.MIN_AGE;

    }

    public String generateCity() {
        return faker.address().city();
    }

    public String generateCountry() {
        return faker.country().name();
    }

    public String generateLanguage(String country) {

        if ("Colombia".equalsIgnoreCase(country)) {
            return "Español";
        }

        String[] languages = {
                "English",
                "Português",
                "Français",
                "Deutsch",
                "Italiano"
        };

        return languages[random.nextInt(languages.length)];

    }

    /**
     * Define aleatoriamente si el registro será
     * una persona o una empresa.
     */
    public boolean isCompany() {

        return random.nextBoolean();

    }

}