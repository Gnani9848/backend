package com.book.dto;

import java.time.LocalDate;

public class FareDetailDTO {
    private Long id;
    private String flightNumber;
    private String airline;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private double basePrice;
    private double taxPercentage;
    private double discountPercentage;    
    private double demandFactor;    
    private double finalPrice;
    private boolean availabilityStatus;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }
    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public double getTaxPercentage() { return taxPercentage; }
    public void setTaxPercentage(double taxPercentage) { this.taxPercentage = taxPercentage; }
    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }
    public double getDemandFactor() { return demandFactor; }
    public void setDemandFactor(double demandFactor) { this.demandFactor = demandFactor; }
    public double getFinalPrice() { return finalPrice; }
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }
    public boolean isAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(boolean availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public FareDetailDTO(Long id, String flightNumber, String airline, String departureCity, String arrivalCity,
            LocalDate departureDate, double basePrice, double taxPercentage, double discountPercentage,
            double demandFactor, double finalPrice, boolean availabilityStatus) {
        super();
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.basePrice = basePrice;
        this.taxPercentage = taxPercentage;
        this.discountPercentage = discountPercentage;
        this.demandFactor = demandFactor;
        this.finalPrice = finalPrice;
        this.availabilityStatus = availabilityStatus;
    }
    public FareDetailDTO() {
        super();
    }
}
