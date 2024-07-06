package com.mihail.currencyconverter.collector.repository;

import com.mihail.currencyconverter.collector.model.RatesCollector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RatesCollectorRepository extends JpaRepository<RatesCollector, Long> {

    @EntityGraph("currency-rates")
    Optional<RatesCollector> findTopByOrderByTimestampDesc();

    @EntityGraph("currency-rates")
    List<RatesCollector> findByTimestampBetween(final LocalDateTime start, final LocalDateTime end);
}