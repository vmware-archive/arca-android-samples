package com.rottentomatoes.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    protected static class Fields {
        public static final String MOVIES = "movies";
    }

    @SerializedName(Fields.MOVIES) private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(final List<Movie> movies) {
        mMovies = movies;
    }
	
}
