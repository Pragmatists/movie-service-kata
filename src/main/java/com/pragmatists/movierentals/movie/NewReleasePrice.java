package com.pragmatists.movierentals.movie;

public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
