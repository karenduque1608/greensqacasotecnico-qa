package com.latam.generator.util;

/**
 * Constantes utilizadas en toda la aplicación.
 */
public final class Constants {

    private Constants() {
    }

    // Base de datos
    public static final String DATABASE_URL = "jdbc:sqlite:latam_users.db";

    // Archivo CSV
    public static final String CSV_FILE = "output/users.csv";

    // Reglas del negocio
    public static final int MIN_AGE = 11;
    public static final int MAX_AGE = 79;

    public static final long MIN_MINOR_DOCUMENT = 11000000L;

}