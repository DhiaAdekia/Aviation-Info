package com.smart4aviation.backend.controllers;

import com.smart4aviation.backend.dtos.AirportResponse;
import com.smart4aviation.backend.dtos.FlightResponse;
import com.smart4aviation.backend.services.AirportInfoService;
import com.smart4aviation.backend.services.FlightInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AviationInfoController {

    private final AirportInfoService airportInfoService;
    private final FlightInfoService flightInfoServiceImpl;

    /**
     * Retrieves flight metrics for a specific flight on the given date.
     *
     * @param flightNumber The flight number for which metrics are requested.
     * @param date         The date for which metrics are being retrieved.
     * @return FlightResponse containing cargo weight, baggage weight, and total weight for the requested flight.
     */
    @GetMapping("/flight")
    public FlightResponse getFlightInfoForFlightNumberAndDate(@RequestParam int flightNumber,
                                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return flightInfoServiceImpl.getFlightInfoForFlightNumberAndDate(flightNumber, date);
    }

    /**
     * Retrieves airport metrics for a specific airport on the given date.
     *
     * @param iataCode The IATA airport code for which metrics are requested.
     * @param date            The date for which metrics are being retrieved.
     * @return AirportResponse containing the number of departing flights, number of arriving flights,
     * total arriving baggage pieces, and total departing baggage pieces for the specified airport and date.
     */
    @GetMapping("/Airport")
    public AirportResponse getAirportInfoForIATAAndDate(@RequestParam String iataCode,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date) {
        return airportInfoService.getAirportInfoForIATACodeAndDate(iataCode, date);
    }

}
