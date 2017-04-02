package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlight() throws Exception {
        mockMvc.perform(get("/flights/flight").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testFlights() throws Exception {
        mockMvc.perform(get("/flights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName").doesNotExist())
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)));
    }

    @Test
    public void testGetPrice()throws Exception{
        MockHttpServletRequestBuilder nullPriceRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Somename\",\"lastName\":\"Someothername\"}},{\"passenger\":{\"firstName\":\"NameB\",\"lastName\":\"NameC\"}]}");

        mockMvc.perform(nullPriceRequest)
                .andExpect(status().isBadRequest());

        MockHttpServletRequestBuilder onePriceRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Somename\",\"lastName\":\"Someothername\"},\"price\":200},{\"passenger\":{\"firstName\":\"NameB\",\"lastName\":\"NameC\"}]}");

        mockMvc.perform(onePriceRequest)
                .andExpect(status().isBadRequest());


        MockHttpServletRequestBuilder normalRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Somename\",\"lastName\":\"Someothername\"},\"price\":200},{\"passenger\":{\"firstName\":\"NameB\",\"lastName\":\"NameC\"},\"price\":150}]}");

        mockMvc.perform(normalRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));

        MockHttpServletRequestBuilder threeTicketsRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Somename\",\"lastName\":\"Someothername\"},\"price\":200},{\"passenger\":{\"firstName\":\"NameB\",\"lastName\":\"NameC\"},\"price\":150},{\"passenger\":{\"firstName\":\"James\",\"lastName\":\"Bond\"},\"price\":150}]}");

        mockMvc.perform(threeTicketsRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(500)));

    }
}
