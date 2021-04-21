package com.zipcoder.cryptonator_api;

import com.zipcoder.cryptonator_api.domain.enums.Currency;
import com.zipcoder.cryptonator_api.services.CryptoService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InformationUpdater {

    private static final Logger log = LoggerFactory.getLogger(InformationUpdater.class);

    private final CryptoService cryptoService;
    private final CryptoAPIService cryptoAPIService;

    @Autowired
    public InformationUpdater(CryptoService cryptoService, CryptoAPIService cryptoAPIService) {
        this.cryptoService = cryptoService;
        this.cryptoAPIService = cryptoAPIService;
    }

    @PostConstruct
    public void getCurrentConversionRates(){
        JSONArray apiResponses = new JSONArray();
        List<Currency> currencyList = new ArrayList<>(Arrays.asList(Currency.values()));
        for(Currency baseCurrency: currencyList){
            List<Currency> otherCurrencies = new ArrayList<>(currencyList);
            otherCurrencies.remove(baseCurrency);
            String baseCurrencyString = baseCurrency.name();
            for(Currency targetCurrency: otherCurrencies){
                String targetCurrencyString = targetCurrency.name();
                JSONObject apiResponse = this.cryptoAPIService.getConversionInfo(baseCurrencyString, targetCurrencyString);
                apiResponses.put(apiResponse);
            }
        }
        cryptoService.saveCurrencyConversionInfo(apiResponses);
    }
}
