package com.latam.automation.models;

public class Passenger {

    private String firstName;
    private String lastName;
    private String document;
    private String city;
    private String country;
    private String language;

    public Passenger() {
    }

    public Passenger(String firstName,
                     String lastName,
                     String document,
                     String city,
                     String country,
                     String language) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.city = city;
        this.country = country;
        this.language = language;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}