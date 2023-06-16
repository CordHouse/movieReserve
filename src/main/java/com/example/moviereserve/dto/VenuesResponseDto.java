package com.example.moviereserve.dto;

import com.example.moviereserve.entity.Venues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenuesResponseDto {
    private String name;
    private int capacity;
    private String venuesType;
    private String possibleTimes;
    private List<SeatsResponseDto> seats;

    public VenuesResponseDto toDo(VenuesSetupRequestDto venues) {
        StringBuilder time = new StringBuilder();
        time.append(venues.getStartTime()).append("-").append(venues.getEndTime());
        return new VenuesResponseDto(
                venues.getName(),
                venues.getCapacity(),
                venues.getVenuesType(),
                time.toString(),
                seatsInfo(venues.getVipSeatMaxNumber(), venues.getCapacity()));
    }

    public List<SeatsResponseDto> seatsInfo(int max, int capacity) {
        List<SeatsResponseDto> seats = new ArrayList<>();
        for(int i = 0; i < capacity; i++) {
            if(i < max) {
                seats.add(new SeatsResponseDto(String.valueOf(i + 1), "VIP"));
                continue;
            }
            seats.add(new SeatsResponseDto(String.valueOf(i + 1), "일반"));
        }
        return seats;
    }
}
