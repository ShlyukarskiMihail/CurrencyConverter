package com.mihail.currencyconverter.collector.scheduler;

import com.mihail.currencyconverter.collector.service.RatesCollectorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateScheduler {

    private final RatesCollectorServiceImpl currencyRateService;

    //@Scheduled(cron = "0 */2 * * * *")
    public void updateCurrencyRates() {
        currencyRateService.updateRatesData();
    }
}