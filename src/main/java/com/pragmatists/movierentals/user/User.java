package com.pragmatists.movierentals.user;

import java.util.ArrayList;
import java.util.List;

import com.pragmatists.movierentals.movie.Movie;

public class User {

	private List<Movie> movies = new ArrayList<Movie>();
	private List<User> friends = new ArrayList<User>();
	
	public List<User> getFriends() {
		return friends;
	}
	
	public void addFriend(User user) {
		friends.add(user);
	}

	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	public List<Movie> movies() {
		return movies;
	}

}
