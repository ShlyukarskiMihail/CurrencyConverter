package com.mihail.currencyconverter.collector.controller.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class RatesCollectorResponse {

    private long timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}