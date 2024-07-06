package com.mihail.currencyconverter.collector.service;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;

import java.util.List;

public interface RatesCollectorService {
    RatesCollectorDTO updateRatesData();

    RateResponse getLatestRatesForCurrency(final String currency);

    List<RateHistory> getHistoricalRates(final String currency, final int period);
}