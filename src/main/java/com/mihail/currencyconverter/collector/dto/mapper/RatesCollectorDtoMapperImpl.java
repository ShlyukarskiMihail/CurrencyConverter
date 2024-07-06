package com.mihail.currencyconverter.collector.dto.mapper;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.controller.response.RatesCollectorResponse;
import org.springframework.stereotype.Component;

@Component
public class RatesCollectorDtoMapperImpl implements RatesCollectorDtoMapper {
    @Override
    public RatesCollectorDTO mapToDto(RatesCollectorResponse response) {
        if (response == null) {
            return null;
        }

        return RatesCollectorDTO.builder()
                .timestamp(response.getTimestamp())
                .base(response.getBase())
                .date(response.getDate())
                .rates(response.getRates())
                .build();
    }
}