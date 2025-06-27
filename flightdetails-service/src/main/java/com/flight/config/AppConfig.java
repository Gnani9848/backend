package com.flight.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // Example: skip id field if you don't want to map it from DTO to entity
        // modelMapper.typeMap(FlightDetailsDTO.class, FlightDetails.class)
        //            .addMappings(mapper -> mapper.skip(FlightDetails::setId));
        return modelMapper;
    }
}
