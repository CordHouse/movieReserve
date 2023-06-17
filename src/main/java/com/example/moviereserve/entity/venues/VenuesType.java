package com.example.moviereserve.entity.venues;

import java.util.Arrays;

public enum VenuesType {
    FIXED_SEAT("FIXED_SEAT"),
    STANDING("STANDING");
    private String type;

    VenuesType(String type){
        this.type = type;
    }

    public static VenuesType findVenuesType(String keyCode){
        return Arrays.stream(VenuesType.values())
                .filter(findVenuesType -> findVenuesType.getType().equals(keyCode))
                .findAny()
                .orElseThrow();
    }

    public String getType(){
        return type;
    }
}
