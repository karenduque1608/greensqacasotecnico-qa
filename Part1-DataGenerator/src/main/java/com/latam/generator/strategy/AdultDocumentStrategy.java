package com.latam.generator.strategy;

import java.util.Random;

/**
 * Genera documentos para mayores de edad.
 * Deben tener entre 9 y 11 dígitos.
 */
public class AdultDocumentStrategy implements DocumentStrategy {

    private final Random random = new Random();

    @Override
    public String generateDocument() {

        int digits = random.nextInt(3) + 9; // 9,10,11

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < digits; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }

}