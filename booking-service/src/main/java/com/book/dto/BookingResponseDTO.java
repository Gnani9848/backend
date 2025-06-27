package com.book.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingResponseDTO {
    private String pnr;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String bookingStatus;
    private double totalFare;
    private int numberOfPassengers;
    private List<PassengerDTO> passengers;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String userId;
    private String fareClass; // Added fareClass field

    public String getPnr() {
        return pnr;
    }
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getDepartureCity() {
        return departureCity;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public double getTotalFare() {
        return totalFare;
    }
    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
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
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public LocalTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFareClass() {
        return fareClass;
    }
    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public BookingResponseDTO(String pnr, String flightNumber, String departureCity, String arrivalCity,
            LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
            String bookingStatus, double totalFare, int numberOfPassengers, List<PassengerDTO> passengers,
            LocalDate bookingDate, LocalTime bookingTime, String userId, String fareClass) {
        super();
        this.pnr = pnr;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.bookingStatus = bookingStatus;
        this.totalFare = totalFare;
        this.numberOfPassengers = numberOfPassengers;
        this.passengers = passengers;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.userId = userId;
        this.fareClass = fareClass;
    }
    public BookingResponseDTO() {
        super();
    }
}
