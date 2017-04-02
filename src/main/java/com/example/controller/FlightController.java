package com.example.controller;

import com.example.builder.FlightBuilder;
import com.example.builder.PassengerBuilder;
import com.example.builder.TicketBuilder;
import com.example.model.Flight;
import com.example.model.Result;
import com.example.model.Ticket;
import com.example.model.Tickets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private Gson gson = new GsonBuilder().create();

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
        try {
            date = format.parse("2017-04-21 14:34");
        } catch (ParseException pe) {
            date = new Date("2017-04-21 14:34");
        }

        return FlightBuilder.aFlight().departs(date)
                .tickets(tickets)
                .build();
    }

    @GetMapping
    public List<Flight> getFlights() {
        List<Ticket> tickets1 = new ArrayList<>();
        tickets1.add(
                TicketBuilder.aTicket().passenger(PassengerBuilder.aPassenger().firstName("Some name").build())
                        .price(200)
                        .build());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("American/Denver"));
        Date date;
        try {
            date = format.parse("2017-04-21 14:34");
        } catch (ParseException pe) {
            date = new Date("2017-04-21 14:34");
        }

        List<Flight> flights = new ArrayList<>();

        flights.add(FlightBuilder.aFlight().departs(date)
                .tickets(tickets1)
                .build());

        return flights;
    }

    @PostMapping("/tickets/total")
    public ResponseEntity<Result> getPrice(@RequestBody String ticketsPayload) {
        if (null == ticketsPayload || ticketsPayload.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Tickets tickets;
        try {
            tickets = gson.fromJson(ticketsPayload, Tickets.class);
        } catch (Exception je) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int sum = 0;

        if (null != tickets && tickets.getTickets().size() > 0)
            sum = tickets.getTickets().stream().mapToInt(ticket -> ticket.getPrice()).sum();
        return new ResponseEntity<>(new Result(sum), HttpStatus.OK);
    }

}
