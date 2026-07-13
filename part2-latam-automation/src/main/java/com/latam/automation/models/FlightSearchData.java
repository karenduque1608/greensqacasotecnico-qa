package com.latam.automation.models;

public class FlightSearchData {

    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String tripType;


    public FlightSearchData() {
    }


    public FlightSearchData(String origin,
                            String destination,
                            String departureDate,
                            String returnDate,
                            String tripType) {

        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.tripType = tripType;
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


    public String getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }


    public String getTripType() {
        return tripType;
    }


    public void setTripType(String tripType) {
        this.tripType = tripType;
    }


    @Override
    public String toString() {

        return "FlightSearchData{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", tripType='" + tripType + '\'' +
                '}';
    }
}