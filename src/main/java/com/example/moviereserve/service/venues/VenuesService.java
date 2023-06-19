package com.example.moviereserve.service.venues;

import com.example.moviereserve.dto.seat.SeatRegisterDto;
import com.example.moviereserve.dto.venues.VenuesResponseDto;
import com.example.moviereserve.dto.venues.VenuesSetupRequestDto;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.entity.venues.Venues;
import com.example.moviereserve.entity.venues.VenuesType;
import com.example.moviereserve.repository.SeatRepository;
import com.example.moviereserve.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenuesService {
    private final VenuesRepository venuesRepository;
    private final SeatRepository seatRepository;

    // 공연장 등록 -> 관리자만 되도록 설정
    @Transactional
    public VenuesResponseDto venuesSetup(VenuesSetupRequestDto venuesSetupRequestDto, User user) {
        Venues venues = new Venues(
                venuesSetupRequestDto.getName(),
                venuesSetupRequestDto.getCapacity(),
                VenuesType.findVenuesType(venuesSetupRequestDto.getVenuesType()),
                venuesSetupRequestDto.getPossibleTimes(),
                user);
        venuesRepository.save(venues);

        for(SeatRegisterDto seatRegister : venuesSetupRequestDto.getSeats()) {
            Seat seat = new Seat(seatRegister.getSeatNumber(), seatRegister.getSeatType(), user, venues);
            seatRepository.save(seat);
        }

        return new VenuesResponseDto().toDo(venuesSetupRequestDto);
    }
}
