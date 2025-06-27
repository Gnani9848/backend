package com.flight.entity;

import jakarta.persistence.*;


import java.time.LocalDate;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "flight_details")


public class FlightDetails {
    
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

	public int getTotalSeats() {
	    return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
	    this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
	    return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
	    this.availableSeats = availableSeats;
	}
	public double getBasePrice() {
	    return basePrice;
	}
	public void setBasePrice(double basePrice) {
	    this.basePrice = basePrice;
	}
	public boolean getActive() {
	    return active;
	}
	public void setActive(boolean active) {
	    this.active = active;
	}
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
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

    @Column(name = "departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime departureTime;
   




    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "active")
    @JsonProperty("active")
    private boolean active;
	public FlightDetails(Long id, String flightNumber, String airline, String departureCity, String arrivalCity,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			int totalSeats, int availableSeats, double basePrice, boolean active) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.basePrice = basePrice;
		this.active = active;
	}
	public FlightDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
