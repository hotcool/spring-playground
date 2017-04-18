package com.example.controller;

import com.example.builder.PassengerBuilder;
import com.example.builder.TicketBuilder;
import com.example.model.Ticket;
import com.example.model.Tickets;
import com.example.util.ReadFixture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc(secure=false)
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
    public void testGetPrice() throws Exception {
        MockHttpServletRequestBuilder normalRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Somename\",\"lastName\":\"Someothername\"},\"price\":200},{\"passenger\":{\"firstName\":\"NameB\",\"lastName\":\"NameC\"},\"price\":150}]}");

        mockMvc.perform(normalRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));

        MockHttpServletRequestBuilder threeTicketsRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ReadFixture.readFixture("src/test/resources/request/Tickets.request", StandardCharsets.UTF_8));

        mockMvc.perform(threeTicketsRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(500)));

        MockHttpServletRequestBuilder oneTicketRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getTicketsFromPojo());

        mockMvc.perform(oneTicketRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(800)));

    }

    @Test
    public void testGetPriceInvalidInput() throws Exception {
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
    }

    private String getTicketsFromPojo() {
        Gson gson = new GsonBuilder().create();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(TicketBuilder.aTicket().passenger(PassengerBuilder.aPassenger().firstName("Peyton").lastName("Manning").build())
        .price(800).build());

        Tickets tickets = new Tickets(ticketList);

        return gson.toJson(tickets);
    }
}
