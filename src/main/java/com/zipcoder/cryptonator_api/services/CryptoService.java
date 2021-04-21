package com.zipcoder.cryptonator_api.services;

import com.zipcoder.cryptonator_api.controller.responses.CurrencyResponse;
import com.zipcoder.cryptonator_api.domain.CurrencyInformation;
import com.zipcoder.cryptonator_api.domain.enums.Currency;
import com.zipcoder.cryptonator_api.exceptions.InvalidCurrencyException;
import com.zipcoder.cryptonator_api.repositories.CryptoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CryptoService {

    private CryptoRepository cryptoRepository;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public void saveCurrencyConversionInfo(JSONArray apiResponses) {
        for (int i = 0; i < apiResponses.length(); i++) {
            CurrencyInformation currencyInformation = new CurrencyInformation();
            JSONObject currencyInfo = apiResponses.optJSONObject(i);

            JSONObject ticker = currencyInfo.optJSONObject("ticker");
            String baseCurrencyString = ticker.optString("base");
            Currency baseCurrencyEnum = Currency.valueOf(baseCurrencyString);
            currencyInformation.setBaseCurrency(baseCurrencyEnum);
            //currencyInformation.setBaseCurrency(Currency.valueOf(currencyInfo.optJSONObject("ticker").optString("base")));
            currencyInformation.setTargetCurrency(Currency.valueOf(currencyInfo.optJSONObject("ticker").optString("target")));
            currencyInformation.setPrice(currencyInfo.optJSONObject("ticker").optDouble("price"));
            cryptoRepository.save(currencyInformation);
        }
    }

    public List<CurrencyInformation> getConversionsForCurrency(String currency) throws InvalidCurrencyException {
        currency = currency.toUpperCase();
        if(Currency.isValidCurrency(currency)){
            return cryptoRepository.findAllConversions(currency);
        }else{
            throw new InvalidCurrencyException("You have not selected a valid currency.");
        }

    }

    public CurrencyResponse getAllCurrencies() {
        ArrayList<Currency> currencies = new ArrayList<>(Arrays.asList(Currency.values()));
        return new CurrencyResponse(currencies);
    }
}
