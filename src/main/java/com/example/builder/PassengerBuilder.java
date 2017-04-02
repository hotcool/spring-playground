package com.example.builder;

import com.example.model.Passenger;

public final class PassengerBuilder {
    private String firstName;
    private String lastName;

    private PassengerBuilder() {
    }

    public static PassengerBuilder aPassenger() {
        return new PassengerBuilder();
    }

    public PassengerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PassengerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Passenger build() {
        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        return passenger;
    }
}
