package com.latam.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con SQLite.
 */
public final class DatabaseConnection {

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                Constants.DATABASE_URL
        );

    }

}