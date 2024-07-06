package com.mihail.currencyconverter.currencystatistic.service;

public interface StatisticsService {
    boolean isRequestDuplicated(String requestId);

    void storeRequestStatistics(String serviceName,
                                String requestId,
                                String client);
}
