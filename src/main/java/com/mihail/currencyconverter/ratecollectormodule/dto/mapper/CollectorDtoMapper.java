package com.mihail.currencyconverter.ratecollectormodule.dto.mapper;


import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;
import com.mihail.currencyconverter.ratecollectormodule.dto.CollectorDto;

public interface CollectorDtoMapper {
    CollectorDto mapToDto(final CollectorResponse response);
}
