package com.mihail.currencyconverter.ratecollectormodule.model.mapper;


import com.mihail.currencyconverter.ratecollectormodule.dto.CollectorDto;
import com.mihail.currencyconverter.ratecollectormodule.model.Collector;

public interface CollectorMapper {
    Collector toEntity(CollectorDto dto);
}
