package com.mihail.currencyconverter.currencystatistic.repository;

import com.mihail.currencyconverter.currencystatistic.model.HistoryCollector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateHistoryRepository extends JpaRepository<HistoryCollector, Long> {

    @EntityGraph("rate-history")
    boolean existsByRequestId(final String requestId);
}