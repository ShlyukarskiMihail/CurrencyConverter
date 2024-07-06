package com.mihail.currencyconverter.collector.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class RatesCollectorDTO {

    private long timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}