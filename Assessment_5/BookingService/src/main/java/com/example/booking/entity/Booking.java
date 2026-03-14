package com.example.booking.entity;

import lombok.Data;

@Data
public class Booking {

	private Long bookingId;
	private Long movieId;
	private Integer tickets;
	private Integer totalAmount;
}
