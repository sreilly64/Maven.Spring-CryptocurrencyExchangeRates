package com.zipcoder.cryptonator_api.controller.responses;

import com.zipcoder.cryptonator_api.domain.enums.Currency;

import java.util.List;

public class CurrencyResponse {

    private List<Currency> currencies;

    public CurrencyResponse(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
