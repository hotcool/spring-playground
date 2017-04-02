package com.example.builder;

import com.example.model.Passenger;
import com.example.model.Ticket;

public final class TicketBuilder {
    private Passenger passenger;
    private int price;

    private TicketBuilder() {
    }

    public static TicketBuilder aTicket() {
        return new TicketBuilder();
    }

    public TicketBuilder passenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public TicketBuilder price(int price) {
        this.price = price;
        return this;
    }

    public Ticket build() {
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(price);
        return ticket;
    }
}
