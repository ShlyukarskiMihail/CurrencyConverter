package com.mihail.currencyconverter.currencystatistic.repository;

import com.mihail.currencyconverter.currencystatistic.model.StatisticsCollector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticCollectorRepository extends JpaRepository<StatisticsCollector, Long> {
    @EntityGraph("statistic-collector")
    boolean existsByRequestId(final String requestId);
}