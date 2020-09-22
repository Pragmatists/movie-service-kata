package com.pragmatists.movierentals.movie;

import com.pragmatists.movierentals.movie.service.MovieService;
import com.pragmatists.movierentals.user.User;
import com.pragmatists.movierentals.user.exception.UserNotLoggedInException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieServiceTest {

    public static final User NOT_LOGGED_IN_USER = null;
    private User user = new User();

    @Test
    void shouldHandleUserNotLoggedInException() {
        MovieService movieService = new TestableMovieService();

        user = NOT_LOGGED_IN_USER;

        Assertions.assertThrows(UserNotLoggedInException.class,
                () -> movieService.getFriendMovies(new User()));
    }

    private class TestableMovieService extends MovieService {
        @Override
        protected User getUser() {
            return user;
        }
    }
}


//PowerMock mockowanie statycznych
//wstrzykiwanie zależności - dependency injection
//metoda którą można nadpisać
