package com.pragmatists.movierentals.movie;
import com.pragmatists.movierentals.movie.CustomerBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CustomerTest {

    @Test
    public void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    public void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Julia").build();
        Movie movie1 = new Movie("Gone with the Wind", new RegularPrice());
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        customer2.addRental(rental1);
    }

    @Test
    public void statementForRegularMovie() {
        Movie movie1 = new Movie("Gone with the Wind", new RegularPrice());
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "Gone with the Wind 3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.rentalsSummary();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForNewReleaseMovie() {
        Movie movie1 = new Movie("Star Wars", new NewReleasePrice());
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "Star Wars 9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customer2.rentalsSummary();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForChildrensMovie() {
        Rental rental1 = new Rental(new Movie("Madagascar", new ChildrenMoviePrice()), 3);
        Customer customer2
                = new CustomerBuilder()
                .withName("Sallie")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Sallie\n" +
                "Madagascar 1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.rentalsSummary();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForManyMovies() {
        Rental rental1 = new Rental(new Movie("Madagascar", new ChildrenMoviePrice()), 6);
        Rental rental2 = new Rental(new Movie("Star Wars", new NewReleasePrice()), 2);
        Rental rental3 = new Rental(new Movie("Gone with the Wind", new RegularPrice()), 8);
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "Madagascar 6.0\n" +
                "Star Wars 6.0\n" +
                "Gone with the Wind 11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customer1.rentalsSummary();
        assertEquals(expected, statement);
    }
}
