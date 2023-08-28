package com.smart4aviation.backend.dtos;

public record FlightResponse(
        double cargoWeight,
        double baggageWeight,
        double totalWeight
) {
}
