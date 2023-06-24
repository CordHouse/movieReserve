package com.example.moviereserve.repository;

import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.venues.Venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    Performance findByVenues(Venues venues);
}
