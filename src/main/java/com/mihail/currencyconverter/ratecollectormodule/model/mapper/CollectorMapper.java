package com.mihail.currencyconverter.ratecollectormodule.model.mapper;

import com.example.ratecollectormodule.dto.CollectorDto;
import com.example.ratecollectormodule.model.Collector;

public interface CollectorMapper {
    Collector toEntity(CollectorDto dto);
}
