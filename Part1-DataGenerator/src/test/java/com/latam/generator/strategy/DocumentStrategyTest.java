package com.latam.generator.strategy;

import com.latam.generator.util.Constants;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentStrategyTest {

    @Test
    void companyDocumentAlwaysStartsWithNineAndHasTenDigits() {
        DocumentStrategy strategy = new CompanyDocumentStrategy();

        IntStream.range(0, 100).forEach(i -> {
            String document = strategy.generateDocument();
            assertTrue(document.startsWith("9"));
            assertTrue(document.length() == 10);
        });
    }

    @Test
    void minorDocumentIsAlwaysGreaterThanOrEqualToTheMinimum() {
        DocumentStrategy strategy = new MinorDocumentStrategy();

        IntStream.range(0, 100).forEach(i -> {
            long document = Long.parseLong(strategy.generateDocument());
            assertTrue(document >= Constants.MIN_MINOR_DOCUMENT);
        });
    }

    @Test
    void adultDocumentHasBetweenNineAndElevenDigits() {
        DocumentStrategy strategy = new AdultDocumentStrategy();

        IntStream.range(0, 100).forEach(i -> {
            int digits = strategy.generateDocument().length();
            assertTrue(digits > 8 && digits < 12);
        });
    }

}
