package com.zipcoder.cryptonator_api.controller;

import com.zipcoder.cryptonator_api.controller.responses.CurrencyResponse;
import com.zipcoder.cryptonator_api.domain.CurrencyInformation;
import com.zipcoder.cryptonator_api.exceptions.InvalidCurrencyException;
import com.zipcoder.cryptonator_api.services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CryptoController {

    private CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping(value = "/currencies")
    public ResponseEntity<CurrencyResponse> getAllCurrencies(){
        CurrencyResponse currencies = cryptoService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }

    @GetMapping(value = "/{currency}")
    public ResponseEntity<List<CurrencyInformation>> getConversionsForCurrency(@PathVariable String currency) throws InvalidCurrencyException {
        List<CurrencyInformation> currencyInformation = cryptoService.getConversionsForCurrency(currency);
        return ResponseEntity.ok(currencyInformation);
    }


}
