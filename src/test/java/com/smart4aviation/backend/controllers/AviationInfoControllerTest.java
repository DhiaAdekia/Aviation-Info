package com.smart4aviation.backend.controllers;

import com.smart4aviation.backend.services.AirportInfoService;
import com.smart4aviation.backend.services.FlightInfoService;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AviationInfoController.class})
@ExtendWith(SpringExtension.class)
class AviationInfoControllerTest {
    @MockBean
    private AirportInfoService airportInfoService;

    @Autowired
    private AviationInfoController aviationInfoController;

    @MockBean
    private FlightInfoService flightInfoServiceImpl;

    /**
     * Method under test: {@link AviationInfoController#getAirportInfoForIATAAndDate(String, Date)}
     */
    @Test
    void testGetAirportInfoForIATAAndDate() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/Airport");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("date", String.valueOf((Object) null))
                .param("iataAirportCode", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aviationInfoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link AviationInfoController#getFlightInfoForFlightNumberAndDate(int, Date)}
     */
    @Test
    void testGetFlightInfoForFlightNumberAndDate() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/flight");
        MockHttpServletRequestBuilder paramResult = getResult.param("date", String.valueOf((Object) null));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("flightNumber", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(aviationInfoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

