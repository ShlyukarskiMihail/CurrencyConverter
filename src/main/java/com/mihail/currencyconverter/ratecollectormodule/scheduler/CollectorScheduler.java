package com.mihail.currencyconverter.ratecollectormodule.scheduler;

import com.example.ratecollectormodule.controller.response.CollectorResponse;
import com.example.ratecollectormodule.dto.CollectorDto;
import com.example.ratecollectormodule.dto.mapper.CollectorDtoMapper;
import com.example.ratecollectormodule.model.Collector;
import com.example.ratecollectormodule.model.mapper.CollectorMapper;
import com.example.ratecollectormodule.repository.CollectorRepository;
import com.example.ratecollectormodule.service.CollectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CollectorScheduler {

    private final CollectorService collectorService;
    private final CollectorDtoMapper collectorDtoMapper;
    private final CollectorMapper collectorMapper;
    private final CollectorRepository collectorRepository;

    @Scheduled(cron = "0 50 17 * * *")
    public void updateCurrencyRates() {
        final CollectorResponse collectorResponse = collectorService.getRatesData();
        final CollectorDto collectorDto = collectorDtoMapper.mapToDto(collectorResponse);
        final Collector collector = collectorMapper.toEntity(collectorDto);

        collectorRepository.save(collector);
        log.info("Saved into DB: {}", collector);
    }
}