package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

    private Passenger passenger;
    private int price;

    public Ticket(Passenger passenger, int price) {
        this.passenger = passenger;
        this.price = price;
    }

    public Ticket() {
    }

    @JsonProperty("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @JsonProperty("Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (price != ticket.price) return false;
        return passenger != null ? passenger.equals(ticket.passenger) : ticket.passenger == null;
    }

    @Override
    public int hashCode() {
        int result = passenger != null ? passenger.hashCode() : 0;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passenger=" + passenger +
                ", price=" + price +
                '}';
    }
}
