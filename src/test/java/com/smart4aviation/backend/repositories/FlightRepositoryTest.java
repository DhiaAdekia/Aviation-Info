package com.smart4aviation.backend.repositories;

import static org.junit.jupiter.api.Assertions.*;


import com.smart4aviation.backend.entities.CargoEntity;
import com.smart4aviation.backend.entities.FlightEntity;
import com.smart4aviation.backend.entities.consignments.Baggage;
import com.smart4aviation.backend.entities.consignments.Cargo;
import com.smart4aviation.backend.entities.consignments.WeightUnit;
import com.smart4aviation.backend.exceptions.FlightEntityNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FlightRepository.class})
@ExtendWith(SpringExtension.class)
class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Method under test: {@link FlightRepository#save(List)}
     */
    @Test
    void testSave() {

        WeightUnit kg = WeightUnit.KG;
        Baggage baggage = new Baggage();
        baggage.setId(1);
        baggage.setWeight(125);
        baggage.setWeightUnit(kg);
        baggage.setPieces(156);

        Cargo cargo = new Cargo();
        cargo.setId(1);
        cargo.setWeight(125);
        cargo.setWeightUnit(kg);
        cargo.setPieces(156);

        List<FlightEntity> flightsEntitiesTest = new ArrayList<>();

        FlightEntity flightEntity = new FlightEntity();

        flightEntity.setFlightNumber(2);
        flightEntity.setDepartureAirportIATACode("ABC");
        flightEntity.setArrivalAirportIATACode("XYZ");
        flightEntity.setDepartureDate(ZonedDateTime.of(2023, 8, 26, 12, 0, 0, 0, ZoneId.of("UTC")));

        ArrayList<FlightEntity> flightList = new ArrayList<>();
        flightList.add(flightEntity);

        flightsEntitiesTest.add(flightEntity);

        flightRepository.save(flightList);

        assertEquals(flightRepository.flightsEntities, flightsEntitiesTest);
    }
}

