package com.mihail.currencyconverter.collector.dto.mapper;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.controller.response.RatesCollectorResponse;

public interface RatesCollectorDtoMapper {
    RatesCollectorDTO mapToDto(final RatesCollectorResponse response);
}
