package com.mihail.currencyconverter.ratecollectormodule.service;

import com.example.ratecollectormodule.controller.response.CollectorResponse;

public interface CurrencyService {

    CollectorResponse getLatestRates();
}
