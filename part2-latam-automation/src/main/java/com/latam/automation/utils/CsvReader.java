package com.latam.automation.utils;

import com.latam.automation.models.Passenger;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String DEFAULT_CSV_PATH =
            "src/test/resources/testdata/users.csv";

    private final String csvPath;

    public CsvReader() {
        this(System.getProperty("testdata.csv", DEFAULT_CSV_PATH));
    }

    public CsvReader(String csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Lee los pasajeros del CSV configurado. Por defecto apunta a la copia de
     * datos de prueba versionada en este módulo; puede repuntarse al CSV real
     * generado por la Parte 1 con
     * {@code -Dtestdata.csv=../Part1-DataGenerator/output/users.csv}.
     */
    public List<Passenger> readPassengers() {

        List<Passenger> passengers = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {

            List<String[]> rows = reader.readAll();

            // Ignorar encabezados
            rows.remove(0);

            for (String[] row : rows) {

                Passenger passenger = new Passenger(
                        row[0], // Nombre
                        row[1], // Apellido
                        row[3], // Documento
                        row[4], // Ciudad
                        row[5], // Pais
                        row[6]  // Idioma
                );

                passengers.add(passenger);
            }

        } catch (IOException | CsvException e) {

            throw new RuntimeException(
                    "Error leyendo archivo CSV: " + csvPath,
                    e
            );
        }

        return passengers;
    }
}