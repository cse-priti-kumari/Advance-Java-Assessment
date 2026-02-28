package com.example.demo.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movierepo;

    public MovieService(MovieRepository movierepo) {
        this.movierepo = movierepo;
    }
    public Movie saveMovie(Movie movie) {
        return movierepo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movierepo.findAll();
    }
    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movierepo.findById(id);
        return movie.orElse(null);
    }
    public void deleteMovie(Long id) {
        movierepo.deleteById(id);
    }
}