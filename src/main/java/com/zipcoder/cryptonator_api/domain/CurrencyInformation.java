package com.zipcoder.cryptonator_api.domain;

import com.zipcoder.cryptonator_api.domain.enums.Currency;

import javax.persistence.*;

@Entity
public class CurrencyInformation {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency baseCurrency;
    @Enumerated(EnumType.STRING)
    private Currency targetCurrency;
    private Double price;

    public CurrencyInformation() {
    }

    public CurrencyInformation(Long id, Currency baseCurrency, Currency targetCurrency, Double price) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
