package com.mihail.currencyconverter.ratecollectormodule.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class CollectorDto {

    private long timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}