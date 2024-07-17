package com.mihail.currencyconverter.ratecollectormodule.scheduler;

import com.mihail.currencyconverter.ratecollectormodule.controller.response.CollectorResponse;
import com.mihail.currencyconverter.ratecollectormodule.dto.CollectorDto;
import com.mihail.currencyconverter.ratecollectormodule.dto.mapper.CollectorDtoMapper;
import com.mihail.currencyconverter.ratecollectormodule.model.Collector;
import com.mihail.currencyconverter.ratecollectormodule.model.mapper.CollectorMapper;
import com.mihail.currencyconverter.ratecollectormodule.repository.CollectorRepository;
import com.mihail.currencyconverter.ratecollectormodule.service.CollectorService;
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

    @Scheduled(cron = "0 */2 * * * *")
    public void updateCurrencyRates() {
        final CollectorResponse collectorResponse = collectorService.getRatesData();
        final CollectorDto collectorDto = collectorDtoMapper.mapToDto(collectorResponse);
        final Collector collector = collectorMapper.toEntity(collectorDto);

        collectorService.sendMessage(collectorResponse);
        collectorRepository.save(collector);
        log.info("Saved into DB: {}", collector);
    }
}