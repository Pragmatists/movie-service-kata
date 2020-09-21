package com.pragmatists.movierentals.movie;

import com.pragmatists.movierentals.user.User;

public class UserBuilder {

    private User[] friends = new User[] {};
    private Movie[] movies = new Movie[] {};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withMovies(Movie... movies) {
        this.movies = movies;
        return this;
    }

    public UserBuilder friendsWith(User... friends) {
        this.friends  = friends;
        return this;
    }

    public User build() {
        User user = new User();
        addMoviesTo(user);
        addFriendsTo(user);
        return user;
    }

    private void addFriendsTo(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }

    private void addMoviesTo(User user) {
        for (Movie movie : movies) {
            user.addMovie(movie);
        }
    }

}
