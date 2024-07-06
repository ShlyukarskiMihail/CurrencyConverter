package com.mihail.currencyconverter.collector.service;

import com.mihail.currencyconverter.collector.controller.response.RatesCollectorResponse;

public interface FixerService {
    RatesCollectorResponse getLatestRates();
}