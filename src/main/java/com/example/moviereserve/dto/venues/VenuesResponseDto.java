package com.example.moviereserve.dto.venues;

import com.example.moviereserve.dto.seat.SeatRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenuesResponseDto {
    private String name;
    private int capacity;
    private String venuesType;
    private String possibleTimes;
    private List<SeatRegisterDto> seats;

    public VenuesResponseDto toDo(VenuesSetupRequestDto venues) {
        return new VenuesResponseDto(
                venues.getName(),
                venues.getCapacity(),
                venues.getVenuesType(),
                venues.getPossibleTimes(),
                venues.getSeats());
    }
}
