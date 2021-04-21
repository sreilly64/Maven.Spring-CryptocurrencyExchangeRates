package com.zipcoder.cryptonator_api.repositories;

import com.zipcoder.cryptonator_api.domain.CurrencyInformation;
import com.zipcoder.cryptonator_api.domain.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRepository extends JpaRepository<CurrencyInformation, Long> {

    @Query(value = "SELECT * FROM CURRENCY_INFORMATION WHERE BASE_CURRENCY=?1", nativeQuery = true)
    List<CurrencyInformation> findAllConversions(String currency);
}
