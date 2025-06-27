package com.flight.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flight.entity.FlightDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, String> {
    
    
    List<FlightDetails> findByDepartureCityAndArrivalCityAndDepartureDate(
            String departureCity, 
            String arrivalCity, 
            LocalDate departureDate
        );
    
    List<FlightDetails> findByAirline(String airline);
    
//    @Query("SELECT f FROM FlightDetails f WHERE f.departureCity = ?1 AND f.arrivalCity = ?2 AND f.departureTime >= ?3 AND f.isActive = true")
//    List<FlightDetails> searchAvailableFlights(String from, String to, LocalDate date);
    @Query("SELECT f FROM FlightDetails f WHERE f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND f.departureDate = :departureDate AND f.active = true")
    List<FlightDetails> searchAvailableFlights(@Param("departureCity") String departureCity, 
                                               @Param("arrivalCity") String arrivalCity, 
                                               @Param("departureDate") LocalDate departureDate);
	
}

