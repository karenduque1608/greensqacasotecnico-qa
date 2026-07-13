package com.latam.generator.repository;

import com.latam.generator.factory.UserFactory;
import com.latam.generator.model.User;
import com.latam.generator.model.UserType;
import com.latam.generator.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRepository implements UserRepository {

    private final UserFactory userFactory;

    public SQLiteRepository() {
        this.userFactory = new UserFactory();
        createTable();
    }

    /**
     * Crea la tabla users si no existe.
     */
    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    last_name TEXT,
                    age INTEGER NOT NULL,
                    document TEXT UNIQUE NOT NULL,
                    city TEXT NOT NULL,
                    country TEXT NOT NULL,
                    language TEXT NOT NULL,
                    type TEXT NOT NULL
                );
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Error creating users table.", e);
        }
    }

    @Override
    public void save(User user) {

        String sql = """
                INSERT INTO users
                (name, last_name, age, document, city, country, language, type)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getDocument());
            statement.setString(5, user.getCity());
            statement.setString(6, user.getCountry());
            statement.setString(7, user.getLanguage());
            statement.setString(8, user.getType().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving user.", e);
        }
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving users.", e);
        }

        return users;
    }

    @Override
    public User findByDocument(String document) {

        String sql = "SELECT * FROM users WHERE document = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, document);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapUser(resultSet);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching user.", e);
        }

        return null;
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting users.", e);
        }
    }

    /**
     * Convierte un registro de la base de datos en un objeto User.
     */
    private User mapUser(ResultSet resultSet) throws SQLException {

        UserType type = UserType.valueOf(resultSet.getString("type"));

        return userFactory.createUser(
                type == UserType.COMPANY,
                resultSet.getString("name"),
                resultSet.getString("last_name"),
                resultSet.getInt("age"),
                resultSet.getString("document"),
                resultSet.getString("city"),
                resultSet.getString("country"),
                resultSet.getString("language")
        );
    }

}