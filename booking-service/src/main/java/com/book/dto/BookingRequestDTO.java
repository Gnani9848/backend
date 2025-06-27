package com.book.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingRequestDTO {
    private String flightNumber;
    private LocalDate departureDate;
    private int numberOfPassengers;
    private List<PassengerDTO> passengers;
    private String fareClass; // economy, business, first, etc.
    private String paymentId;

    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public List<PassengerDTO> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<PassengerDTO> passengers) {
        this.passengers = passengers;
    }
    public String getFareClass() {
        return fareClass;
    }
    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BookingRequestDTO(String flightNumber, LocalDate departureDate,
            int numberOfPassengers, List<PassengerDTO> passengers, String fareClass, String paymentId) {
        super();
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.numberOfPassengers = numberOfPassengers;
        this.passengers = passengers;
        this.fareClass = fareClass;
        this.paymentId = paymentId;
    }
    public BookingRequestDTO() {
        super();
    }
}