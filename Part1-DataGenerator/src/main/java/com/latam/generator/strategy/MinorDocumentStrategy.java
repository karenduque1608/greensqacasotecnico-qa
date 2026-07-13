package com.latam.generator.strategy;

import com.latam.generator.util.Constants;

import java.util.Random;

/**
 * Genera documentos para menores de edad.
 * Deben iniciar desde 11000000.
 */
public class MinorDocumentStrategy implements DocumentStrategy {

    private final Random random = new Random();

    @Override
    public String generateDocument() {

        long value = Constants.MIN_MINOR_DOCUMENT
                + random.nextInt(8_000_000);

        return String.valueOf(value);

    }

}