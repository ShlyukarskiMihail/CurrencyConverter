package com.mihail.currencyconverter.currencystatistic.repository;

import com.mihail.currencyconverter.currencystatistic.model.StatisticsCollector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticCollectorRepository extends JpaRepository<StatisticsCollector, Long> {

    boolean existsByRequestId(final String requestId);
}