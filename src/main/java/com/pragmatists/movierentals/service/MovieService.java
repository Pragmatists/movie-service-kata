package com.pragmatists.movierentals.service;

import java.util.ArrayList;
import java.util.List;

import com.pragmatists.movierentals.dao.MovieDAO;
import com.pragmatists.movierentals.movie.Movie;
import com.pragmatists.movierentals.user.User;
import com.pragmatists.movierentals.exception.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieService {
	@Autowired
	private MovieDAO movieDAO;

	public List<Movie> getFriendMovies(User user, User loggedInUser) throws UserNotLoggedInException {
		validate(loggedInUser);

		return user.isFriendsWith(loggedInUser)
				? tripsFrom(user)
				: noMovies();
	}

	private ArrayList<Movie> noMovies() {
		return new ArrayList<Movie>();
	}

	private void validate(User loggedInUser) {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	private List<Movie> tripsFrom(User user) {
		return movieDAO.moviesByUser(user);
	}
	
}
