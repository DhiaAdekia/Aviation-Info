package com.smart4aviation.backend.repositories;

import com.smart4aviation.backend.entities.CargoEntity;
import com.smart4aviation.backend.entities.consignments.Baggage;
import com.smart4aviation.backend.entities.consignments.Cargo;
import com.smart4aviation.backend.exceptions.CargoEntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CargoRepository {

    public final List<CargoEntity> cargoEntities = new ArrayList<>();
    public void save(List<CargoEntity> cargotList) {
        cargoEntities.addAll(cargotList);
    }

    public CargoEntity findCargoEntityByFlightId(Long flightId) {
        return cargoEntities.stream()
                .filter(x -> x.getFlightId().equals(flightId))
                .findFirst()
                .orElseThrow(() -> new CargoEntityNotFoundException("this flight has No Consignments"));
    }
}
