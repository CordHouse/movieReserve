package com.example.moviereserve.repository;

import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.venues.Venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumberAndVenues(String seatNumber, Venues venues);
    Optional<List<Seat>> findAllByVenues(Venues venues);

    @Query("select s from Seat s where s.status = :status")
    Optional<List<Seat>> findAllByPerformance(@Param("status") String status);
}
