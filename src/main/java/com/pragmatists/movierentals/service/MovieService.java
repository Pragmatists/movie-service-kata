package com.pragmatists.movierentals.service;

import java.util.ArrayList;
import java.util.List;

import com.pragmatists.movierentals.dao.MovieDAO;
import com.pragmatists.movierentals.movie.Movie;
import com.pragmatists.movierentals.user.User;
import com.pragmatists.movierentals.user.UserSession;
import com.pragmatists.movierentals.exception.UserNotLoggedInException;

public class MovieService {

	//shows a list of user movies if user is a friend of logged in user
	public List<Movie> getFriendMovies(User user) throws UserNotLoggedInException {
		List<Movie> movieList = new ArrayList<Movie>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				movieList = MovieDAO.findMoviesByUser(user);
			}
			return movieList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}
