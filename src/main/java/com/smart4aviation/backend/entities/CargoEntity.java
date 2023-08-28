package com.smart4aviation.backend.entities;

import com.smart4aviation.backend.entities.consignments.Baggage;
import com.smart4aviation.backend.entities.consignments.Cargo;
import lombok.Data;

import java.util.List;

@Data
public class CargoEntity {
    private Long flightId;
    private List<Baggage> baggage;
    private List<Cargo> cargo;
}
