package com.search.controller;
import io.swagger.v3.oas.annotations.Operation;  
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.dto.SearchCriteriaDTO;
import com.search.dto.SearchResultDTO;
import com.search.service.SearchService;


import java.util.List;


@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    // Define multiple date time formatters to handle different input formats
    @PostMapping("/flights")
    @Operation(summary = "Search for flights based on criteria")
    public List<SearchResultDTO> searchFlights(
        @RequestBody SearchCriteriaDTO searchCriteria
    ) {
        return searchService.searchFlights(searchCriteria);
    }
    @PostMapping("/flights/advanced")
    @Operation(summary = "Advanced flight search with additional filters")
    public List<SearchResultDTO> advancedSearchFlights(
        @RequestBody SearchCriteriaDTO searchCriteria,
        @RequestParam(required = false, defaultValue = "") List<String> airlines,
        @RequestParam(required = false, defaultValue = "0") double minPrice,
        @RequestParam(required = false, defaultValue = "999999") double maxPrice
    ) {
        return searchService.advancedSearch(searchCriteria, airlines, minPrice, maxPrice);
    }
}
