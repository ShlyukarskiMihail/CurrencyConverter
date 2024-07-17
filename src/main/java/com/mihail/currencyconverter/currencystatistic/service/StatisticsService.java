package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.HistoricalRate;
import java.util.List;

public interface StatisticsService {
    boolean isRequestDuplicated(final String requestId);

    void storeRequestStatistics(final String serviceName,
                                final String requestId,
                                final String client);

    void storeHistoryRequestStatistics(final String serviceName,
                                       final String requestId,
                                       final String client,
                                       final List<HistoricalRate> rateHistories);
}
