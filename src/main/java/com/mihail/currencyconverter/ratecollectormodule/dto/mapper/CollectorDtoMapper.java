package com.mihail.currencyconverter.ratecollectormodule.dto.mapper;

import com.example.ratecollectormodule.controller.response.CollectorResponse;
import com.example.ratecollectormodule.dto.CollectorDto;

public interface CollectorDtoMapper {
    CollectorDto mapToDto(final CollectorResponse response);
}
