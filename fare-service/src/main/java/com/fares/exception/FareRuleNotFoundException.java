package com.fares.exception;


public class FareRuleNotFoundException extends RuntimeException {
    public FareRuleNotFoundException(String message) {
        super(message);
    }
}