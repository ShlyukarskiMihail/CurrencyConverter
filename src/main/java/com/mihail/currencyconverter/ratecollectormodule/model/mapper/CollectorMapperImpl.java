package com.mihail.currencyconverter.ratecollectormodule.model.mapper;

import com.mihail.currencyconverter.ratecollectormodule.dto.CollectorDto;
import com.mihail.currencyconverter.ratecollectormodule.model.Collector;
import com.mihail.currencyconverter.ratecollectormodule.model.Rate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CollectorMapperImpl implements CollectorMapper {

    @Override
    public Collector toEntity(CollectorDto dto) {
        if (dto == null) {
            return null;
        }

        Collector collector = new Collector();
        collector.setTimestamp(LocalDateTime.now());
        collector.setBaseCurrency(dto.getBase());

        var rateList = dto.getRates().entrySet().stream()
                .map(entry -> {
                    Rate rate = new Rate();
                    rate.setCurrencyCode(entry.getKey());
                    rate.setRateToEuro(entry.getValue());
                    rate.setCollector(collector);
                    return rate;
                }).toList();

        collector.setRateList(rateList);
        return collector;
    }
}