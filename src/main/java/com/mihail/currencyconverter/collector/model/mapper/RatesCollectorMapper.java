package com.mihail.currencyconverter.collector.model.mapper;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.model.RatesCollector;

public interface RatesCollectorMapper {
    RatesCollector toEntity(RatesCollectorDTO dto);
}