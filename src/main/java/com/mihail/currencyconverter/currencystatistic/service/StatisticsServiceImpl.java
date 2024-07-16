package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.StatisticsCollector;
import com.mihail.currencyconverter.currencystatistic.repository.StatisticCollectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import static com.mihail.currencyconverter.currencystatistic.utils.handler.BusinessErrorCodes.DUPLICATED_REQUEST;

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

        if (isRequestDuplicated(requestId)) {
            throw new DataIntegrityViolationException(DUPLICATED_REQUEST.getDescription() + " for request_id: " + requestId);
        } else {
            requestStatisticRepository.save(StatisticsCollector.builder()
                    .serviceName(serviceName)
                    .requestId(requestId)
                    .timestamp(LocalDateTime.now())
                    .clientId(client)
                    .build());
        }
    }
}