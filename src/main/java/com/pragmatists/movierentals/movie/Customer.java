package com.pragmatists.movierentals.movie;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Rentals rentals = new Rentals();
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String rentalsSummary() {
        return new CustomerRentalsSummary().rentalsSummary(rentals, name);
    }
}
