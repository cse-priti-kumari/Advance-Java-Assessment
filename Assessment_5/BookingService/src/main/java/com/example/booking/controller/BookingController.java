package com.example.booking.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.entity.Booking;


@RestController
@RequestMapping("/bookings")
public class BookingController {
	Map<Long, Booking> bookingsList = new HashMap<>();
	
	@GetMapping
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> bookings = new ArrayList<>(bookingsList.values());
		if(!bookings.isEmpty()) {
			return ResponseEntity.ok(bookings);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Booking> bookMovie(@RequestBody Booking newBooking) {
		bookingsList.put(newBooking.getBookingId(), newBooking);
		if(newBooking.getMovieId() != null) {
			URI location = URI.create("/bookings/" + newBooking.getBookingId());
			return ResponseEntity.created(location).body(newBooking);
		}
		return ResponseEntity.badRequest().body(newBooking);
	}
}
