package com.mihail.currencyconverter.ratecollectormodule.service;

import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;


public interface CurrencyService {

    CollectorResponse getLatestRates();
}