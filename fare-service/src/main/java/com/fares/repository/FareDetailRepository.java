package com.fares.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fares.entitty.FareDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FareDetailRepository extends JpaRepository<FareDetail, String> {
	
    Optional<FareDetail> findByFlightNumberAndDepartureDate(String flightNumber, LocalDate departureDate);
    List<FareDetail> findByDepartureCityAndArrivalCityAndDepartureDate(
        String departureCity, String arrivalCity, LocalDate departureDate
    );
}