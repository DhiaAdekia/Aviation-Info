package com.smart4aviation.backend.repositories;

import com.smart4aviation.backend.entities.FlightEntity;
import com.smart4aviation.backend.exceptions.FlightEntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {

    public final List<FlightEntity> flightsEntities = new ArrayList<>();
    public void save(List<FlightEntity> flightList) {
        flightsEntities.addAll(flightList);
    }


    public List<Integer> findNumberOfDepartingFlightsByIATACodeAndDate(String iataCode, LocalDate date) {
        return flightsEntities.stream()
                .filter(x -> x.getDepartureAirportIATACode().equals(iataCode) && x.getDepartureDate().toLocalDate().equals(date))
                .map(FlightEntity::getFlightNumber)
                .toList();
    }

    public List<Integer> findNumberOfArrivingFlightsByIATACodeAndDate(String iataCode, LocalDate date) {
        return flightsEntities.stream()
                .filter(x -> x.getArrivalAirportIATACode().equals(iataCode) && x.getDepartureDate().toLocalDate().equals(date))
                .map(FlightEntity::getFlightNumber)
                .toList();
    }

    public List<Long> findDepartingFlightsById(String iataCode, LocalDate date) {
        return flightsEntities.stream()
                .filter(x -> x.getDepartureAirportIATACode().equals(iataCode) && x.getDepartureDate().toLocalDate().equals(date))
                .map(FlightEntity::getFlightId)
                .toList();
    }

    public List<Long> findArrivingFlightsById(String iataCode, LocalDate date) {
        return flightsEntities.stream()
                .filter(x -> x.getArrivalAirportIATACode().equals(iataCode) && x.getDepartureDate().toLocalDate().equals(date))
                .map(FlightEntity::getFlightId)
                .toList();
    }

    public FlightEntity findFlightEntityByFlightNumberAndDate(int flightNumber, LocalDate date) {
        return flightsEntities.stream()
                .filter(x -> x.getFlightNumber() == flightNumber && x.getDepartureDate().toLocalDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new FlightEntityNotFoundException("Flight Number Not Found"));
    }
}
