package com.latam.generator.strategy;

import java.util.Random;

/**
 * Genera documentos para empresas.
 * Siempre comienzan por 9.
 */
public class CompanyDocumentStrategy implements DocumentStrategy {

    private final Random random = new Random();

    @Override
    public String generateDocument() {

        StringBuilder builder = new StringBuilder("9");

        for (int i = 0; i < 9; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();

    }

}