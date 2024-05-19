package com.example.bookmyshow.repositories;

import com.example.bookmyshow.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
