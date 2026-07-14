package com.latam.generator.service;

import com.latam.generator.model.Company;
import com.latam.generator.model.Person;
import com.latam.generator.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    void ageMustBeGreaterThan10AndLessThan80() {
        assertFalse(validationService.isValidAge(10));
        assertTrue(validationService.isValidAge(11));
        assertTrue(validationService.isValidAge(79));
        assertFalse(validationService.isValidAge(80));
    }

    @Test
    void documentMustBeUniqueAmongGeneratedOnes() {
        Set<String> documents = new HashSet<>();
        documents.add("123456789");

        assertFalse(validationService.isDocumentUnique("123456789", documents));
        assertTrue(validationService.isDocumentUnique("987654321", documents));
    }

    @Test
    void fullNameMustBeUniqueAmongGeneratedOnes() {
        Set<String> names = new HashSet<>();
        names.add("Juan Perez");

        assertFalse(validationService.isUniqueName("Juan Perez", names));
        assertTrue(validationService.isUniqueName("Ana Gomez", names));
    }

    @Test
    void languageCannotBeSpanishWhenCountryIsNotColombia() {
        User user = new Person("Ana", "Gomez", 30, "123456789", "Madrid", "España", "Español");

        assertFalse(validationService.isValidLanguage(user));
    }

    @Test
    void spanishIsAllowedWhenCountryIsColombia() {
        User user = new Person("Ana", "Gomez", 30, "123456789", "Bogota", "Colombia", "Español");

        assertTrue(validationService.isValidLanguage(user));
    }

    @Test
    void nonSpanishLanguageIsAllowedForAnyCountry() {
        User user = new Company("Acme", 30, "9123456789", "Madrid", "España", "English");

        assertTrue(validationService.isValidLanguage(user));
    }

}
