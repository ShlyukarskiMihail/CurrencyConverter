package com.mihail.currencyconverter.collector.service;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.dto.mapper.RatesCollectorDtoMapper;
import com.mihail.currencyconverter.collector.model.mapper.RatesCollectorMapper;
import com.mihail.currencyconverter.collector.repository.RatesCollectorRepository;
import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class RatesCollectorServiceImpl implements RatesCollectorService {

    private final RatesCollectorRepository ratesCollectorRepository;
    private final FixerServiceImpl fixerService;
    private final RatesCollectorDtoMapper fixerApiResponseMapper;
    private final RatesCollectorMapper ratesCollectorMapper;

    @Transactional
    @Override
    public RatesCollectorDTO updateRatesData() {
        var fixerApiResponse = fixerService.getLatestRates();
        var fixerApiResponseDTO = fixerApiResponseMapper.mapToDto(fixerApiResponse);
        var ratesCollector = ratesCollectorMapper.toEntity(fixerApiResponseDTO);
        ratesCollectorRepository.save(ratesCollector);
        log.info("Saved Into DB: {}", ratesCollector);

        return fixerApiResponseDTO;
    }

    @Override
    public RateResponse getLatestRatesForCurrency(final String currency) {
        var latestRate = ratesCollectorRepository.findTopByOrderByTimestampDesc();

        if (latestRate.isPresent()) {
            var currencyRate = latestRate.get().getCurrencyRatesList().stream()
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
    public List<RateHistory> getHistoricalRates(final String currency, final int period) {
        final LocalDateTime end = LocalDateTime.now();
        final LocalDateTime start = end.minusHours(period);

        var rateCollectors = ratesCollectorRepository.findByTimestampBetween(start, end);
        return rateCollectors.stream()
                .flatMap(rc -> rc.getCurrencyRatesList().stream()
                        .filter(rate -> currency.equals(rate.getCurrencyCode()))
                        .map(rate -> RateHistory.builder()
                                .currency(currency)
                                .rate(rate.getRateToEuro())
                                .timestamp(rc.getTimestamp())
                                .build())
                ).collect(Collectors.toList());
    }
}