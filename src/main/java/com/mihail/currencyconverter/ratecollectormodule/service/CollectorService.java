package com.mihail.currencyconverter.ratecollectormodule.service;

import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.model.HistoricalRate;
import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;

import java.util.List;

public interface CollectorService {

    void sendMessage(CollectorResponse collectorResponse);

    CollectorResponse getRatesData();

    RateResponse getLatestRateForCurrency(final String currency);

    List<HistoricalRate> getHistoricalRates(final String currency, final int period);
}