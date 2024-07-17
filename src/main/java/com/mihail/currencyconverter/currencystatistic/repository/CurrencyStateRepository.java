package com.mihail.currencyconverter.currencystatistic.repository;

import com.mihail.currencyconverter.currencystatistic.model.CurrencyState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyStateRepository extends JpaRepository<CurrencyState, Long> {

    boolean existsByRequestId(final String requestId);
}