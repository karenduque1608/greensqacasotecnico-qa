package com.latam.automation.utils;

import com.latam.automation.models.Passenger;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String CSV_PATH =
            "src/test/resources/testdata/users.csv";


    public List<Passenger> readPassengers() {

        List<Passenger> passengers = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_PATH))) {

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
                    "Error leyendo archivo CSV: " + CSV_PATH,
                    e
            );
        }

        return passengers;
    }
}