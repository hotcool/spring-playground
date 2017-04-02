package com.example.controller;

import com.example.builder.FlightBuilder;
import com.example.builder.PassengerBuilder;
import com.example.builder.TicketBuilder;
import com.example.model.Flight;
import com.example.model.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/flight")
    public Flight getFlight() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(
                TicketBuilder.aTicket().passenger(PassengerBuilder.aPassenger().firstName("Some name").lastName("Some other name").build())
                        .price(200)
                        .build());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("American/Denver"));
        Date date;
        try{
            date = format.parse("2017-04-21 14:34");
        }catch (ParseException pe){
            date = new Date("2017-04-21 14:34");
        }

        return FlightBuilder.aFlight().departs(date)
                .tickets(tickets)
                .build();
    }

    @GetMapping
    public List<Flight> getFlights(){
        List<Ticket> tickets1 = new ArrayList<>();
        tickets1.add(
                TicketBuilder.aTicket().passenger(PassengerBuilder.aPassenger().firstName("Some name").build())
                        .price(200)
                        .build());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("American/Denver"));
        Date date;
        try{
            date = format.parse("2017-04-21 14:34");
        }catch (ParseException pe){
            date = new Date("2017-04-21 14:34");
        }

        List<Flight> flights = new ArrayList<>();

        flights.add(FlightBuilder.aFlight().departs(date)
                .tickets(tickets1)
                .build());

        return flights;
    }

}
