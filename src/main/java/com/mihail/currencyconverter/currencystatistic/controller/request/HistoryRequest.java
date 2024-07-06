package com.mihail.currencyconverter.currencystatistic.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {

    private String requestId;
    private long timestamp;
    private String client;
    private String currency;
    private int period;
}