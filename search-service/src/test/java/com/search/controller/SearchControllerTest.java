package com.search.controller;

import com.search.dto.SearchCriteriaDTO;
import com.search.dto.SearchResultDTO;
import com.search.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private SearchService searchService;

    private SearchCriteriaDTO criteria;
    private SearchResultDTO result;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criteria = new SearchCriteriaDTO();
        criteria.setDepartureCity("CityA");
        criteria.setArrivalCity("CityB");
        // set other fields as needed

        result = new SearchResultDTO();
        result.setFlightNumber("FL123");
        result.setAirline("TestAir");
        // set other fields as needed
    }

    @Test
    void testSearchFlights() {
        List<SearchResultDTO> expected = Collections.singletonList(result);
        when(searchService.searchFlights(criteria)).thenReturn(expected);

        List<SearchResultDTO> actual = searchController.searchFlights(criteria);

        assertEquals(expected, actual);
        verify(searchService, times(1)).searchFlights(criteria);
    }

    @Test
    void testAdvancedSearchFlights() {
        List<SearchResultDTO> expected = Arrays.asList(result);
        List<String> airlines = Arrays.asList("TestAir");
        double minPrice = 100.0;
        double maxPrice = 2000.0;

        when(searchService.advancedSearch(criteria, airlines, minPrice, maxPrice)).thenReturn(expected);

        List<SearchResultDTO> actual = searchController.advancedSearchFlights(criteria, airlines, minPrice, maxPrice);

        assertEquals(expected, actual);
        verify(searchService, times(1)).advancedSearch(criteria, airlines, minPrice, maxPrice);
    }
}