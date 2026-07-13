package com.latam.generator.service;

import com.latam.generator.model.User;
import com.latam.generator.util.Constants;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Servicio encargado de exportar los usuarios
 * a un archivo CSV.
 */
public class CsvService {

    /**
     * Genera el archivo CSV con los usuarios.
     *
     * @param users Lista de usuarios a exportar.
     */
    public void exportUsers(List<User> users) {

        createOutputDirectory();

        try (CSVWriter writer = new CSVWriter(new FileWriter(Constants.CSV_FILE))) {

            String[] header = {
                    "Nombre",
                    "Apellido",
                    "Edad",
                    "Documento",
                    "Ciudad",
                    "Pais",
                    "Idioma",
                    "Tipo"
            };

            writer.writeNext(header);

            for (User user : users) {

                String[] row = {
                        user.getName(),
                        user.getLastName(),
                        String.valueOf(user.getAge()),
                        user.getDocument(),
                        user.getCity(),
                        user.getCountry(),
                        user.getLanguage(),
                        user.getType().name()
                };

                writer.writeNext(row);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error generating CSV file.",
                    e
            );

        }

    }

    /**
     * Crea la carpeta output si no existe.
     */
    private void createOutputDirectory() {

        File directory = new File("output");

        if (!directory.exists()) {
            directory.mkdirs();
        }

    }

}