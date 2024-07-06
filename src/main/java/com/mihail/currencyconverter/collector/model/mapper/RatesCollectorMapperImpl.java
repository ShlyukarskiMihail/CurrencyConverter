package com.mihail.currencyconverter.collector.model.mapper;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.model.CurrencyRates;
import com.mihail.currencyconverter.collector.model.RatesCollector;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class RatesCollectorMapperImpl implements RatesCollectorMapper {

    @Override
    public RatesCollector toEntity(RatesCollectorDTO dto) {
        if (dto == null) {
            return null;
        }

        var entity = new RatesCollector();
        entity.setTimestamp(LocalDateTime.now());
        entity.setBaseCurrency(dto.getBase());

        var currencyRatesList = dto.getRates().entrySet().stream()
                .map(entry -> {
                    CurrencyRates rate = new CurrencyRates();
                    rate.setCurrencyCode(entry.getKey());
                    rate.setRateToEuro(entry.getValue());
                    rate.setRatesCollector(entity);
                    return rate;
                }).collect(Collectors.toList());
        entity.setCurrencyRatesList(currencyRatesList);
        return entity;
    }
}