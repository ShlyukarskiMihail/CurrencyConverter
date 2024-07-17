package com.mihail.currencyconverter.currencystatistic.repository;

import com.mihail.currencyconverter.currencystatistic.model.CurrencyHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory, Long> {

    @EntityGraph("history-collector-graph")
    boolean existsByRequestId(final String requestId);
}