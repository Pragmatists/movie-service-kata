package com.pragmatists.movierentals.movie;

import java.util.List;

public class ReportBuilder {
    private StringBuilder result;
    public ReportBuilder() {
        result = new StringBuilder();
    }

    public ReportBuilder prepareReport(String name, List<Rental> rentals) {
        getHeader(name);
        getChargePart(rentals);
        getPointsPart(rentals);
        return this;
    }

    void getHeader(String name) {
        result.append("Rental Record for ").append(name).append("\n");
    }

    void getChargePart(List<Rental> rentals) {
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

    void getPointsPart(List<Rental> rentals) {
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

    @Override
    public String toString() {
        return result.toString();
    }
}
