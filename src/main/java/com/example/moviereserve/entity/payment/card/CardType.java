package com.example.moviereserve.entity.payment.card;

import java.util.Arrays;

public enum CardType {
    CREDIT_CARD("신용카드"),
    CHECK_CARD("체크카드");

    private String cardType;

    CardType(String cardType){
        this.cardType = cardType;
    }

    public static CardType getCardName(String bankName){
        return Arrays.stream(CardType.values())
                .filter(bank -> bank.getCardType().equals(bankName))
                .findAny()
                .orElseThrow();
    }

    public String getCardType(){
        return cardType;
    }
}
