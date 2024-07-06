package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.HistoryCollector;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import com.mihail.currencyconverter.currencystatistic.repository.RateHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class HistoryServiceImpl implements HistoryService {

    private final RateHistoryRepository historyRatesRepository;

    public boolean isHistoryRequestDuplicated(final String requestId) {
        return historyRatesRepository.existsByRequestId(requestId);
    }

    public void storeHistoryRequestStatistics(final String serviceName,
                                              final String requestId,
                                              final String client,
                                              final List<RateHistory> rateHistories) {
        final HistoryCollector history = HistoryCollector.builder()
                .serviceName(serviceName)
                .requestId(requestId)
                .clientId(client)
                .build();

        for (final RateHistory rateHistory : rateHistories) {
            rateHistory.setHistoryCollector(history);
        }

        history.setRateHistory(rateHistories);
        historyRatesRepository.save(history);
        log.info(history);
    }
}