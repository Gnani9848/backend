package com.search.service;

import com.search.dto.FlightDetailsDTO;
import com.search.dto.SearchCriteriaDTO;
import com.search.dto.SearchResultDTO;
import com.search.dto.fareDetailDTO;
import com.search.feign.FareClient;
import com.search.feign.FlightDetailsClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private FlightDetailsClient flightDetailsClient;

    @Mock
    private FareClient fareClient;

    private SearchCriteriaDTO criteria;
    private FlightDetailsDTO flight;
    private fareDetailDTO fare;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        criteria = new SearchCriteriaDTO();
        criteria.setDepartureCity("CityA");
        criteria.setArrivalCity("CityB");
        criteria.setDepartureDate(LocalDate.of(2024, 6, 1));

        flight = new FlightDetailsDTO();
        flight.setFlightNumber("FL123");
        flight.setAirline("TestAir");
        flight.setDepartureCity("CityA");
        flight.setArrivalCity("CityB");
        flight.setDepartureDate(LocalDate.of(2024, 6, 1));
        flight.setDepartureTime(LocalTime.of(10, 0));
        flight.setArrivalTime(LocalTime.of(12, 0));
        flight.setAvailableSeats(5);

        fare = new fareDetailDTO();
        fare.setFlightNumber("FL123");
        fare.setBasePrice(100.0);
        fare.setFinalPrice(120.0);
    }

    @Test
    void testSearchFlights() {
        when(flightDetailsClient.searchFlights(anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(flight));
        when(fareClient.searchFares(anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(fare));

        List<SearchResultDTO> results = searchService.searchFlights(criteria);

        assertEquals(1, results.size());
        SearchResultDTO result = results.get(0);
        assertEquals("FL123", result.getFlightNumber());
        assertEquals("TestAir", result.getAirline());
        assertEquals(120.0, result.getFinalPrice());
        assertTrue(result.getDuration() > 0);
    }

    @Test
    void testAdvancedSearch() {
        when(flightDetailsClient.searchFlights(anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(flight));
        when(fareClient.searchFares(anyString(), anyString(), anyString()))
                .thenReturn(Collections.singletonList(fare));

        List<String> airlines = Arrays.asList("TestAir");
        double minPrice = 100.0;
        double maxPrice = 200.0;

        List<SearchResultDTO> results = searchService.advancedSearch(criteria, airlines, minPrice, maxPrice);

        assertEquals(1, results.size());
        assertEquals("TestAir", results.get(0).getAirline());
        assertTrue(results.get(0).getFinalPrice() >= minPrice && results.get(0).getFinalPrice() <= maxPrice);
    }
}