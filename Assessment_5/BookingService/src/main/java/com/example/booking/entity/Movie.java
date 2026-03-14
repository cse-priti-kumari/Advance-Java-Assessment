package com.example.booking.entity;

import lombok.Data;

@Data
public class Movie {
	private Long id;
	private String name;
	private String language;
	private Double price;
}

