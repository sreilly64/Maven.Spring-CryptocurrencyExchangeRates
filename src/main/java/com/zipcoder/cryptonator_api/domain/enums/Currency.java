package com.zipcoder.cryptonator_api.domain.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum Currency {

    USD, BTC, DOGE;

//    USD("USD"), BTC("BTC"), DOGE("DOGE");
//
//    private final String name;
//
//    private Currency(String name){
//        this.name = name;
//    }
//
//    public String getName(){
//        return this.name;
//    }

    public static boolean isValidCurrency(String currency) {
        currency = currency.toUpperCase();
//        ArrayList<Currency> currencies = new ArrayList<>(Arrays.asList(Currency.values()));
//        return currencies.contains(Currency.valueOf(currency));
        try{
            Currency.valueOf(currency);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }


}
