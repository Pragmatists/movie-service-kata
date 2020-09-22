package com.pragmatists.movierentals.movie.service;

import java.util.ArrayList;
import java.util.List;

import com.pragmatists.movierentals.movie.dao.MovieDAO;
import com.pragmatists.movierentals.movie.Movie;
import com.pragmatists.movierentals.user.User;
import com.pragmatists.movierentals.user.UserSession;
import com.pragmatists.movierentals.user.exception.UserNotLoggedInException;

public class MovieService {

	//shows a list of user movies if user is a friend of logged in user
	public List<Movie> getFriendMovies(User user) throws UserNotLoggedInException {
		List<Movie> movieList = new ArrayList<Movie>();
		User loggedUser = getUser();
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

	protected User getUser() {
		return UserSession.getInstance().getLoggedUser();
	}


}
