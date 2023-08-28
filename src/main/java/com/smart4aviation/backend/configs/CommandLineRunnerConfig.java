package com.smart4aviation.backend.configs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.backend.entities.CargoEntity;
import com.smart4aviation.backend.entities.FlightEntity;
import com.smart4aviation.backend.repositories.CargoRepository;
import com.smart4aviation.backend.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CommandLineRunnerConfig implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final CargoRepository cargoRepository;

    @Override
    public void run(String... args) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        final TypeReference<List<FlightEntity>> flightsTypeReference = new TypeReference<>() {
        };
        final TypeReference<List<CargoEntity>> cargostypeReference = new TypeReference<>() {
        };
        final InputStream flightsInputStream = TypeReference.class.getResourceAsStream("/data/Flights.json");
        final InputStream cargosInputStream = TypeReference.class.getResourceAsStream("/data/Cargo.json");

        try {
            final List<FlightEntity> flightEntities = mapper.readValue(flightsInputStream, flightsTypeReference);
            flightRepository.save(flightEntities);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to save FlightEntities.", e);
        }

        try {
            final List<CargoEntity> cargoEntities = mapper.readValue(cargosInputStream, cargostypeReference);
            cargoRepository.save(cargoEntities);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to save CargoEntities.", e);
        }
    }
}
