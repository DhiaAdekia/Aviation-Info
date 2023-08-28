package com.smart4aviation.backend.services;

import com.smart4aviation.backend.dtos.FlightResponse;
import com.smart4aviation.backend.entities.CargoEntity;
import com.smart4aviation.backend.entities.FlightEntity;
import com.smart4aviation.backend.entities.consignments.Consignments;
import com.smart4aviation.backend.entities.consignments.WeightUnit;
import com.smart4aviation.backend.exceptions.BadRequestException;
import com.smart4aviation.backend.repositories.CargoRepository;
import com.smart4aviation.backend.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInfoService {

    private final CargoRepository cargoRepository;
    private final FlightRepository flightRepository;

    /**
     * Get flight weight statistics by Flight Number and date.
     *
     * @param flightNumber is a flight number
     * @param date         is a date
     * @return flight weight statistics object that contains cargo weight,
     * baggage weight and total weight for requested Flight.
     */
    public FlightResponse getFlightInfoForFlightNumberAndDate(int flightNumber,
                                                              LocalDate date) {

        if (flightNumber < 1000 || flightNumber > 9999) {
            throw new BadRequestException("Flight Number code is in a wrong Format");
        }

        final FlightEntity flightEntity = flightRepository.findFlightEntityByFlightNumberAndDate(flightNumber, date);
        final CargoEntity cargoEntity = cargoRepository.findCargoEntityByFlightId(flightEntity.getFlightId());

        double cargoWeight = calculateSumOfWeights(cargoEntity.getCargo());
        double baggageWeight = calculateSumOfWeights(cargoEntity.getBaggage());
        double totalWeight = cargoWeight + baggageWeight;

        return new FlightResponse(cargoWeight, baggageWeight, totalWeight);
    }

    public static double calculateSumOfWeights(List<? extends Consignments> consignmentsList) {
        return consignmentsList.stream()
                .mapToDouble(consignment -> {
                    double weightInKg = consignment.getWeight();
                    if (consignment.getWeightUnit() == WeightUnit.LB) {
                        weightInKg *= 0.45359237; // Convert lbs to kgs
                    }
                    return weightInKg;
                })
                .sum();
    }
}
