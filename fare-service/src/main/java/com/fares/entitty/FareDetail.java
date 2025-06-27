package com.fares.entitty;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "fare_details")
public class FareDetail {
//    
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
   
	@Id
    @Column(name = "flight_number", unique = true)
    private String flightNumber;

    @Column(name = "airline")
    private String airline;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "tax_percentage")
    private double taxPercentage;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "demand_factor")
    private double demandFactor;

    @Column(name = "final_price")
    private double finalPrice;

    @Column(name = "availability_status")
    private boolean availabilityStatus;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
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

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDemandFactor() {
		return demandFactor;
	}

	public void setDemandFactor(double demandFactor) {
		this.demandFactor = demandFactor;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public FareDetail( String flightNumber, String airline, String departureCity, String arrivalCity,
			LocalDate departureDate, double basePrice, double taxPercentage, double discountPercentage,
			double demandFactor, double finalPrice, boolean availabilityStatus) {
		super();
		
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

	public FareDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}