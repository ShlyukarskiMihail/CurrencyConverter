package com.mihail.currencyconverter.currencystatistic.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateResponse {

    private String currency;
    private BigDecimal rate;
    private LocalDateTime timestamp;
}