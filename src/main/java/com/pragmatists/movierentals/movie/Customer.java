package com.pragmatists.movierentals.movie;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String getReport() {
        StringBuilder result = getHeader();
        getChargePart(result);
        getPointsPart(result);
        return result.toString();
    }

    private void getPointsPart(StringBuilder result) {
        int frequentRenterPoints = 0;
        for (Rental each : rentals) {
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
        }
        result.append("You earned ").append(String.valueOf(frequentRenterPoints)).append(" frequent renter points");
    }

    private void getChargePart(StringBuilder result) {
        double totalAmount = 0;
        for (Rental each : rentals) {
            double thisAmount = 0;

            //determine amounts for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }
            // show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(String.valueOf(thisAmount)).append("\n");
            totalAmount += thisAmount;
        }
        result.append("Amount owed is ").append(String.valueOf(totalAmount)).append("\n");
    }

    private StringBuilder getHeader() {
        return new StringBuilder("Rental Record for " + getName() + "\n");
    }
}
