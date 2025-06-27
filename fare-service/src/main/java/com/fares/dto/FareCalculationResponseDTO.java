package com.fares.dto;



public class FareCalculationResponseDTO {
    private String flightNumber;
    private Double basePrice;
    private Double taxAmount;
    private Double serviceFee;
    private Double fuelSurcharge;
    private Double totalFare;
    private Double discountedFare;
    private String discountCode;
    private Double discountAmount;
    private Integer passengerCount;
    private Double farePerPassenger;

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Double getFuelSurcharge() {
		return fuelSurcharge;
	}
	public void setFuelSurcharge(Double fuelSurcharge) {
		this.fuelSurcharge = fuelSurcharge;
	}
	public Double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}
	public Double getDiscountedFare() {
		return discountedFare;
	}
	public void setDiscountedFare(Double discountedFare) {
		this.discountedFare = discountedFare;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}
	public Double getFarePerPassenger() {
		return farePerPassenger;
	}
	public void setFarePerPassenger(Double farePerPassenger) {
		this.farePerPassenger = farePerPassenger;
	}

	public FareCalculationResponseDTO(String flightNumber, Double basePrice, Double taxAmount, Double serviceFee,
			Double fuelSurcharge, Double totalFare, Double discountedFare, String discountCode, Double discountAmount,
			Integer passengerCount, Double farePerPassenger) {
		super();
		this.flightNumber = flightNumber;
		this.basePrice = basePrice;
		this.taxAmount = taxAmount;
		this.serviceFee = serviceFee;
		this.fuelSurcharge = fuelSurcharge;
		this.totalFare = totalFare;
		this.discountedFare = discountedFare;
		this.discountCode = discountCode;
		this.discountAmount = discountAmount;
		this.passengerCount = passengerCount;
		this.farePerPassenger = farePerPassenger;
	}
	public FareCalculationResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
