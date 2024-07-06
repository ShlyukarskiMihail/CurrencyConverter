package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.StatisticsCollector;
import com.mihail.currencyconverter.currencystatistic.repository.StatisticCollectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticCollectorRepository requestStatisticRepository;

    public boolean isRequestDuplicated(final String requestId) {
        return requestStatisticRepository.existsByRequestId(requestId);
    }

    public void storeRequestStatistics(final String serviceName,
                                       final String requestId,
                                       final String client) {
        requestStatisticRepository.save(StatisticsCollector.builder()
                .serviceName(serviceName)
                .requestId(requestId)
                .timestamp(LocalDateTime.now())
                .clientId(client)
                .build());
    }
}