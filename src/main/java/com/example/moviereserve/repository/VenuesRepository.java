package com.example.moviereserve.repository;

import com.example.moviereserve.entity.venues.Venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenuesRepository extends JpaRepository<Venues, Long> {
}
