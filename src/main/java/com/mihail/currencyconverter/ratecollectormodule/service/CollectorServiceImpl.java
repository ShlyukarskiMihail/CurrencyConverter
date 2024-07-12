package com.mihail.currencyconverter.ratecollectormodule.service;

import com.example.ratecollectormodule.controller.response.CollectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    private final CurrencyService currencyService;

    @Override
    public CollectorResponse getRatesData() {
        return currencyService.getLatestRates();
    }
}