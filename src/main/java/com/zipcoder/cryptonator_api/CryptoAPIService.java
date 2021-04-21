package com.zipcoder.cryptonator_api;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoAPIService {

    private static final Logger log = LoggerFactory.getLogger(CryptoAPIService.class);
    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final String baseUrl = "https://api.cryptonator.com/api/ticker";


    public JSONObject getConversionInfo(String baseCurrency, String targetCurrency) {
        String url = baseUrl + "/" + baseCurrency + "-" + targetCurrency;
        log.info("RestTemplate url: {}", url);
        try{
            String response = this.restTemplate.getForObject(url, String.class);
            return new JSONObject(response);
        }catch(JSONException e){
            return new JSONObject();
        }

    }
}
