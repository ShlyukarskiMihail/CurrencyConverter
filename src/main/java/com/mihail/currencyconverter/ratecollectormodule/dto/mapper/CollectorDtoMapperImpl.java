package com.mihail.currencyconverter.ratecollectormodule.dto.mapper;

import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;
import com.mihail.currencyconverter.ratecollectormodule.dto.CollectorDto;
import org.springframework.stereotype.Component;

@Component
public class CollectorDtoMapperImpl implements CollectorDtoMapper {

    @Override
    public CollectorDto mapToDto(CollectorResponse response) {
        return response == null ? null : CollectorDto.builder()
                .timestamp(response.getTimestamp())
                .base(response.getBase())
                .date(response.getDate())
                .rates(response.getRates())
                .build();
    }
}