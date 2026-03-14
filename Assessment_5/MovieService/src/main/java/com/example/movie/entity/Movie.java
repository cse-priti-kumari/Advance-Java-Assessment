package com.example.movie.entity;

import lombok.Data;

@Data
public class Movie {
	private Long id;
	private String name;
	private String language;
	private Double price;
}
