package com.latam.generator.service;

import com.latam.generator.model.User;

import java.util.Set;

/**
 * Contiene las reglas de negocio para validar
 * los usuarios generados.
 */
public class ValidationService {

    /**
     * Valida la edad.
     */
    public boolean isValidAge(int age) {
        return age > 10 && age < 80;
    }

    /**
     * Valida que el documento no exista.
     */
    public boolean isDocumentUnique(String document,
                                    Set<String> documents) {

        return !documents.contains(document);

    }

    /**
     * Valida que la combinación nombre + apellido
     * no exista.
     */
    public boolean isUniqueName(String fullName,
                                Set<String> names) {

        return !names.contains(fullName);

    }

    /**
     * Si el país no es Colombia,
     * el idioma no puede ser Español.
     */
    public boolean isValidLanguage(User user) {

        if (!user.getCountry().equalsIgnoreCase("Colombia")) {

            return !user.getLanguage()
                    .equalsIgnoreCase("Español");

        }

        return true;

    }

}