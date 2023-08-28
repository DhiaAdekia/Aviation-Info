package com.smart4aviation.backend.services;

import com.smart4aviation.backend.dtos.AirportResponse;
import com.smart4aviation.backend.entities.consignments.Baggage;
import com.smart4aviation.backend.exceptions.BadRequestException;
import com.smart4aviation.backend.repositories.CargoRepository;
import com.smart4aviation.backend.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportInfoService {

    private final CargoRepository cargoRepository;
    private final FlightRepository flightRepository;

    /**
     * Get flight statistics by IATA Airport Code and date.
     *
     * @param iataCode is an IATA code
     * @param date is a date
     * @return flight statistics data that contains number of flights
     * departing from this airport, number of flights arriving to this airport,
     * total number (pieces) of baggage arriving to this airport
     * and total number (pieces) of baggage departing from this airport.
     */
    public AirportResponse getAirportInfoForIATACodeAndDate(String iataCode,
                                                            LocalDate date) {

        if (!iataCode.matches("[A-Z]{3}")) {
            throw new BadRequestException("IATA code is in a wrong Format");
        }

        int departingBaggageTotalPieces = 0;
        int arrivingBaggageTotalPieces = 0;

        final List<Long> idOfDepartingFlights = flightRepository.findDepartingFlightsById(iataCode, date);
        final List<Long> idOfArrivingFlights = flightRepository.findArrivingFlightsById(iataCode, date);

        for (Long id : idOfDepartingFlights) {
            departingBaggageTotalPieces += calculateAllBaggage(id);
        }
        for (Long id : idOfArrivingFlights) {
            arrivingBaggageTotalPieces += calculateAllBaggage(id);
        }

        final List<Integer> numberOfDepartingFlights = flightRepository.findNumberOfDepartingFlightsByIATACodeAndDate(iataCode, date);
        final List<Integer> numberOfArrivingFlights = flightRepository.findNumberOfArrivingFlightsByIATACodeAndDate(iataCode, date);

        return new AirportResponse(numberOfDepartingFlights, numberOfArrivingFlights, departingBaggageTotalPieces, arrivingBaggageTotalPieces);
    }

    private static int calculateSumOfPieces(List<Baggage> baggageList) {
        return baggageList.stream()
                .mapToInt(Baggage::getPieces)
                .sum();
    }


    private int calculateAllBaggage(Long flightId) {
        return calculateSumOfPieces(cargoRepository.findCargoEntityByFlightId(flightId).getBaggage());
    }
}
