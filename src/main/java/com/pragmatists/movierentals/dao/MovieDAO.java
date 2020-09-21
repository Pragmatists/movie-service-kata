package com.pragmatists.movierentals.dao;

import java.util.List;

import com.pragmatists.movierentals.movie.Movie;
import com.pragmatists.movierentals.user.User;

public class MovieDAO {

	public static List<Movie> findMoviesByUser(User user) {
		throw new RuntimeException("Not Implemented yet");
	}

	public List<Movie> moviesByUser(User user) {
		return MovieDAO.findMoviesByUser(user);
	}

}
