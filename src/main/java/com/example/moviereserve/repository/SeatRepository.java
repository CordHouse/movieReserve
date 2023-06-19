package com.example.moviereserve.repository;

import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.venues.Venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumberAndVenues(String seatNumber, Venues venues);
}
