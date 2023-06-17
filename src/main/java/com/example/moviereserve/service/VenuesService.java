package com.example.moviereserve.service;

import com.example.moviereserve.dto.venues.VenuesResponseDto;
import com.example.moviereserve.dto.venues.VenuesSetupRequestDto;
import com.example.moviereserve.entity.User;
import com.example.moviereserve.entity.Venues;
import com.example.moviereserve.entity.VenuesType;
import com.example.moviereserve.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenuesService {
    private final VenuesRepository venuesRepository;

    @Transactional
    public VenuesResponseDto venuesSetup(VenuesSetupRequestDto venuesSetupRequestDto, User user) {
        Venues venues = new Venues(
                venuesSetupRequestDto.getName(),
                venuesSetupRequestDto.getCapacity(),
                VenuesType.findVenuesType(venuesSetupRequestDto.getVenuesType()),
                venuesSetupRequestDto.getStartTime(),
                venuesSetupRequestDto.getEndTime());
        venuesRepository.save(venues);

        return new VenuesResponseDto().toDo(venuesSetupRequestDto);
    }
}
