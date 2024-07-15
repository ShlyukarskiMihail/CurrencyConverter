package com.mihail.currencyconverter.ratecollectormodule.service;

import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;

import java.util.List;

public interface CurrencyService {

    CollectorResponse getLatestRates();
}