package com.mihail.currencyconverter.currencystatistic.service;

import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import java.util.List;

public interface HistoryService {
    void storeHistoryRequestStatistics(String serviceName,
                                       String requestId,
                                       String client,
                                       List<RateHistory> rateHistories);
}