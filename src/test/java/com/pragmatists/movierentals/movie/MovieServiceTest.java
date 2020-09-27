package com.pragmatists.movierentals.movie;

import com.pragmatists.movierentals.dao.MovieDAO;
import com.pragmatists.movierentals.exception.UserNotLoggedInException;
import com.pragmatists.movierentals.service.MovieService;
import com.pragmatists.movierentals.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.pragmatists.movierentals.movie.UserBuilder.aUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    private static final User UNUSED_USER = null;
    private static final User ANOTHER_USER = new User();
    private static final Movie SHREK = new Movie("Shrek",new RegularPrice());
    private static final User GUEST = null;
    private static final User LOGGED_IN_USER = new User();
    private static final Movie RAMBO = new Movie("Rambo", new RegularPrice());

    @Mock
    private MovieDAO movieDAO;

    @InjectMocks
    private MovieService movieService = new MovieService();

    @Test
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        Assertions.assertThrows(UserNotLoggedInException.class, () -> {
            movieService.getFriendMovies(UNUSED_USER, GUEST);
        });
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends() {
        User friend = aUser()
                .friendsWith(ANOTHER_USER)
                .withMovies(SHREK)
                .build();

        List<Movie> friendMovies = movieService.getFriendMovies(friend, LOGGED_IN_USER);

        assertThat(friendMovies.size()).isEqualTo(0);
    }

    @Test
    public void should_return_trips_when_users_are_friends() {
        User friend = aUser()
                .friendsWith(ANOTHER_USER, LOGGED_IN_USER)
                .withMovies(SHREK, RAMBO)
                .build();
        when(movieDAO.moviesByUser(friend)).thenReturn(friend.movies());

        List<Movie> friendMovies = movieService.getFriendMovies(friend, LOGGED_IN_USER);

        assertThat(friendMovies.size()).isEqualTo(2);
    }
}
