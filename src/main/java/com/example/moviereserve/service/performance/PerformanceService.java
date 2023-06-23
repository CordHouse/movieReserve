package com.example.moviereserve.service.performance;

import com.example.moviereserve.dto.performance.*;
import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.performance.price.Price;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.entity.venues.Venues;
import com.example.moviereserve.exception.NotFoundPerformanceException;
import com.example.moviereserve.repository.PerformanceRepository;
import com.example.moviereserve.repository.PriceRepository;
import com.example.moviereserve.repository.SeatRepository;
import com.example.moviereserve.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PriceRepository priceRepository;
    private final VenuesRepository venuesRepository;
    private final SeatRepository seatRepository;

    // 공연 등록
    @Transactional
    public PerformanceResponseDto register(PerformanceRequestDto performanceRequestDto, User user) {
        Price price = new Price(
                performanceRequestDto.getPrices().getVip(),
                performanceRequestDto.getPrices().getCommon()
        );
        priceRepository.save(price);

        Venues venues = venuesRepository.findById(performanceRequestDto.getVenueId()).orElseThrow(); // 해당 공연장이 없을 경우 예외 처리

        Performance performance = new Performance(
                venues,
                performanceRequestDto.getName(),
                performanceRequestDto.getCapacity(),
                performanceRequestDto.getDate(),
                performanceRequestDto.getStartTime(),
                performanceRequestDto.getEndTime(),
                price,
                user
        );
        performanceRepository.save(performance);

        venues.getSeat().forEach(v -> v.setVenues(venues));

        return new PerformanceResponseDto().toDo(performance, price);
    }

    // 공연 정보 조회
    @Transactional
    public PerformanceInfoResponseDto getPerformanceInfo() {
        List<Performance> performance = performanceRepository.findAll();
        if(performance.isEmpty()) {
            throw new NotFoundPerformanceException();
        }

        List<PerformanceInfo> performanceInfoList = new ArrayList<>();
        performance.forEach(v -> performanceInfoList.add(new PerformanceInfo().toDo(v)));
        return new PerformanceInfoResponseDto().toDo(performanceInfoList);
    }

    // 공연 잔여 좌석 조회
    @Transactional
    public PerformanceSeatsInfoResponseDto getPerformanceSeatsInfo(long id) {
        Performance performance = performanceRepository.findById(id).orElseThrow();
        List<Seat> seat = seatRepository.findAllByVenues(performance.getVenues()).orElseThrow();

        List<PerformanceSeatInfo> performanceSeatInfoList = new ArrayList<>();
        seat.forEach(v -> performanceSeatInfoList.add(new PerformanceSeatInfo().toDo(v)));
        return new PerformanceSeatsInfoResponseDto().toDo(performance.getId(), performanceSeatInfoList);
    }
}
