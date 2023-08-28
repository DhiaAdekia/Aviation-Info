package com.smart4aviation.backend.entities.consignments;

import lombok.Data;

@Data
public class Consignments {
    private int id;
    private int weight;
    private WeightUnit weightUnit;
    private int pieces;
}
