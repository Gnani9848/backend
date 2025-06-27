package com.search.dto;

import java.time.LocalDate;


public class SearchCriteriaDTO {
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private int passengers;
    private String travelClass;
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
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public SearchCriteriaDTO(String departureCity, String arrivalCity, LocalDate departureDate, int passengers,
			String travelClass) {
		super();
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureDate = departureDate;
		this.passengers = passengers;
		this.travelClass = travelClass;
	}
	public SearchCriteriaDTO() {
		super();
		// TODO Auto-generated constructor stub
	} 
   
}