package com.latam.automation.models;

public class Flight {

    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String price;


    public Flight() {
    }


    public Flight(String origin,
                  String destination,
                  String departureDate,
                  String arrivalDate,
                  String price) {

        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getDepartureDate() {
        return departureDate;
    }


    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }


    public String getArrivalDate() {
        return arrivalDate;
    }


    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {

        return "Flight{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}