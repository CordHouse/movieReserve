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

    // 공연장 등록 -> 관리자만 되도록 설정
    @Transactional
    public VenuesResponseDto venuesSetup(VenuesSetupRequestDto venuesSetupRequestDto) {
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
