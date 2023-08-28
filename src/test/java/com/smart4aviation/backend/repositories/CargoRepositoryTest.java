package com.smart4aviation.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.smart4aviation.backend.entities.CargoEntity;
import com.smart4aviation.backend.entities.consignments.Baggage;
import com.smart4aviation.backend.entities.consignments.Cargo;
import com.smart4aviation.backend.entities.consignments.WeightUnit;
import com.smart4aviation.backend.exceptions.CargoEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CargoRepository.class})
@ExtendWith(SpringExtension.class)
class CargoRepositoryTest {
    @Autowired
    private CargoRepository cargoRepository;

    /**
     * Method under test: {@link CargoRepository#save(List)}
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

        List<CargoEntity> cargoEntitiesTest = new ArrayList<>();

        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setBaggage(new ArrayList<Baggage>() {{
            add(baggage);
        }});
        cargoEntity.setCargo(new ArrayList<Cargo>() {{
            add(cargo);
        }});
        cargoEntity.setFlightId(Long.valueOf(1));

        ArrayList<CargoEntity> cargotList = new ArrayList<>();
        cargotList.add(cargoEntity);

        cargoEntitiesTest.add(cargoEntity);

        cargoRepository.save(cargotList);

        assertEquals(cargoRepository.cargoEntities, cargoEntitiesTest);
    }


}

