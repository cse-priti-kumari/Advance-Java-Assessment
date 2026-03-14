package com.example.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.booking.entity.Movie;

@FeignClient(name = "MOVIE-SERVICE")
public interface MovieClient {
	
	@GetMapping("/{id}")
	Movie getMovieById(@PathVariable Long id);
}
