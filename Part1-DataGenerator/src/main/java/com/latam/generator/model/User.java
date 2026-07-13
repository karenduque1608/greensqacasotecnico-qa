package com.latam.generator.model;

/**
 * Clase base para todos los usuarios generados.
 * Demuestra:
 * - Encapsulamiento
 * - Abstracción
 */
public abstract class User {

    private String name;
    private String lastName;
    private int age;
    private String document;
    private String city;
    private String country;
    private String language;

    protected User() {
    }

    protected User(String name,
                   String lastName,
                   int age,
                   String document,
                   String city,
                   String country,
                   String language) {

        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.document = document;
        this.city = city;
        this.country = country;
        this.language = language;
    }

    /**
     * Cada tipo de usuario conoce su propio tipo.
     * Esto permite usar polimorfismo en lugar de instanceof.
     */
    public abstract UserType getType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "type=" + getType() +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", document='" + document + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}