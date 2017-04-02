package com.example.builder;

import com.example.model.Flight;
import com.example.model.Ticket;

import java.util.Date;
import java.util.List;

public final class FlightBuilder {
    private Date departs;
    private List<Ticket> tickets;

    private FlightBuilder() {
    }

    public static FlightBuilder aFlight() {
        return new FlightBuilder();
    }

    public FlightBuilder departs(Date departs) {
        this.departs = departs;
        return this;
    }

    public FlightBuilder tickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public Flight build() {
        Flight flight = new Flight();
        flight.setDeparts(departs);
        flight.setTickets(tickets);
        return flight;
    }
}
