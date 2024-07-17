package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.CurrencyHistory;
import com.mihail.currencyconverter.currencystatistic.model.HistoricalRate;
import com.mihail.currencyconverter.currencystatistic.model.CurrencyState;
import com.mihail.currencyconverter.currencystatistic.repository.CurrencyHistoryRepository;
import com.mihail.currencyconverter.currencystatistic.repository.CurrencyStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static com.mihail.currencyconverter.currencystatistic.utils.handler.BusinessErrorCodes.DUPLICATED_REQUEST;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsServiceImpl implements StatisticsService {

    private final CurrencyStateRepository requestStatisticRepository;
    private final CurrencyHistoryRepository rateHistoryRepository;

    public boolean isRequestDuplicated(final String requestId) {
        return requestStatisticRepository.existsByRequestId(requestId);
    }

    public boolean isHistoryRequestDuplicated(final String requestId) {
        return rateHistoryRepository.existsByRequestId(requestId);
    }

    @Override
    public void storeRequestStatistics(final String serviceName,
                                       final String requestId,
                                       final String client) {

        if (isRequestDuplicated(requestId)) {
            throw new DataIntegrityViolationException(DUPLICATED_REQUEST.getDescription() + " for request_id: " + requestId);
        }
        requestStatisticRepository.save(CurrencyState.builder()
                .serviceName(serviceName)
                .requestId(requestId)
                .timestamp(LocalDateTime.now())
                .clientId(client)
                .build());
    }

    @Override
    public void storeHistoryRequestStatistics(final String serviceName,
                                              final String requestId,
                                              final String client,
                                              final List<HistoricalRate> rates) {
        if (isHistoryRequestDuplicated(requestId)) {
            throw new DataIntegrityViolationException(DUPLICATED_REQUEST.getDescription() + " for request_id: " + requestId);
        }
            final CurrencyHistory history = CurrencyHistory.builder()
                    .serviceName(serviceName)
                    .requestId(requestId)
                    .clientId(client)
                    .build();

            for (final HistoricalRate rate : rates) {
                rate.setHistoryCollector(history);
            }

            history.setHistoricalRates(rates);
            rateHistoryRepository.save(history);
            log.info(history);
    }
}