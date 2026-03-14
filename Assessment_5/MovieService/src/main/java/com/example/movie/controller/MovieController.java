package com.example.movie.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.entity.Movie;


@RestController
@RequestMapping("/movies")
public class MovieController {
	Map<Long, Movie> moviesList = new HashMap<>();
	
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovie() {
		List<Movie> movies = new ArrayList<>(moviesList.values());
		if(!movies.isEmpty()) {
			return ResponseEntity.ok(movies);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
		if(moviesList.containsKey(id)) {
			Movie movie = moviesList.get(id);
			return ResponseEntity.ok(movie);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Movie> addMovie(@RequestBody Movie newMovie) {
		moviesList.put(newMovie.getId(), newMovie);
		URI location = URI.create("/movies/" + newMovie.getId());
		return ResponseEntity.created(location).body(newMovie);
	}
}
