package com.smart4aviation.backend.entities;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class FlightEntity {
    private Long flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private ZonedDateTime departureDate;
}
