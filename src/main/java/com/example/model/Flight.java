package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Flight {

    private Date departs;
    private List<Ticket> tickets;

    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }

    public Flight() {
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("Departs")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    @JsonProperty("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (departs != null ? !departs.equals(flight.departs) : flight.departs != null) return false;
        return tickets != null ? tickets.equals(flight.tickets) : flight.tickets == null;
    }

    @Override
    public int hashCode() {
        int result = departs != null ? departs.hashCode() : 0;
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departs=" + departs +
                ", tickets=" + tickets +
                '}';
    }
}
