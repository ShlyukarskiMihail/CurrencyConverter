package com.mihail.currencyconverter.ratecollectormodule.service;

import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;
import com.mihail.currencyconverter.ratecollectormodule.repository.CollectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    private final CollectorRepository collectorRepository;
    private final CurrencyService currencyService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public CollectorResponse getRatesData() {
        return currencyService.getLatestRates();
    }

    public void sendMessage(CollectorResponse collectorResponse) {
        final String message = "Collecting Latest Rates: \n" + collectorResponse;
        rabbitTemplate.convertAndSend("rateCollectorExchange", "latestRate", message);
    }

    @Override
    public RateResponse getLatestRateForCurrency(String currency) {
        var latestRate = collectorRepository.findTopByOrderByTimestampDesc();

        if (latestRate.isPresent()) {
            var currencyRate = latestRate.get().getRateList().stream()
                    .filter(rate -> currency.equals(rate.getCurrencyCode()))
                    .findFirst();
            if (currencyRate.isPresent()) {
                return RateResponse.builder()
                        .currency(currency)
                        .rate(currencyRate.get().getRateToEuro())
                        .timestamp(latestRate.get().getTimestamp())
                        .build();
            } else {
                throw new RuntimeException("No rate found for currency: " + currency);
            }
        } else {
            throw new RuntimeException("No rates found");
        }
    }

    @Override
    public List<RateHistory> getHistoricalRates(String currency, int period) {

        var rateCollectors = collectorRepository.findByTimestampBetween(LocalDateTime.now(), LocalDateTime.now().minusHours(period));
        return rateCollectors.stream()
                .flatMap(rc -> rc.getRateList().stream()
                        .filter(rate -> currency.equals(rate.getCurrencyCode()))
                        .map(rate -> RateHistory.builder()
                                .currency(currency)
                                .rate(rate.getRateToEuro())
                                .timestamp(rc.getTimestamp())
                                .build())
                ).collect(Collectors.toList());
    }
}