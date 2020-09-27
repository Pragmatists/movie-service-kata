package com.pragmatists.movierentals.movie;

public class Movie {

    public static final int CHILDREN = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    String title;
    Price price;

    public Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
    public String getTitle() {
        return title;
    }


}
