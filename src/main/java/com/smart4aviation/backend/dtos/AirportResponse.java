package com.smart4aviation.backend.dtos;

import java.util.List;

public record AirportResponse(
        List<Integer> numberOfDepartingFlights,
        List<Integer> numberOfArrivingFlights,
        int DepartingBaggageTotalPieces,
        int ArrivingBaggageTotalPieces
) {
}
