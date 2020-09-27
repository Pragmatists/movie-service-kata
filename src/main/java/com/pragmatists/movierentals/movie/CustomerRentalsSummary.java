package com.pragmatists.movierentals.movie;

public class CustomerRentalsSummary {

    public String rentalsSummary(Rentals rentals, String customerName) {
        SummaryReport summary = new SummaryReport();

        summary.add(headerLine(customerName));
        summary.add(rentalsDescriptions(rentals));
        summary.add(footerLine(rentals));
        summary.add(fequentRenter(rentals));

        return summary.asString();
    }

    String headerLine(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

    String rentalsDescriptions(Rentals rentals) {
        return rentals.getDescription();
    }

    String footerLine(Rentals rentals) {
        return "Amount owed is " + rentals.getTotalCharge() + "\n";
    }

    String fequentRenter(Rentals rentals) {
        return "You earned " + String.valueOf(rentals.getFrequentRenterPoints()) + " frequent renter points";
    }

}
