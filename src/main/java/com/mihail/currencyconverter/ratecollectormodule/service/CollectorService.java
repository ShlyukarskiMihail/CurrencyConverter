package com.mihail.currencyconverter.ratecollectormodule.service;

import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;

import java.util.List;

public interface CollectorService {

    CollectorResponse getRatesData();

    RateResponse getLatestRateForCurrency(final String currency);

    List<RateHistory> getHistoricalRates(final String currency, final int period);
}