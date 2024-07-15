package com.mihail.currencyconverter.currencystatistic.controller.api;

import com.mihail.currencyconverter.currencystatistic.controller.request.HistoryRequest;
import com.mihail.currencyconverter.currencystatistic.controller.request.RateRequest;
import com.mihail.currencyconverter.base.GatewayService;
import com.mihail.currencyconverter.currencystatistic.service.StatisticsService;
import com.mihail.currencyconverter.currencystatistic.service.StatisticsServiceImpl;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/json_api")
public class JsonApiController {

    private final GatewayService gatewayService;
    private final StatisticsService statisticsService;

    @PostMapping(value = "/current", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCurrentRates(@RequestBody @Valid RateRequest request) {
        if (statisticsService.isRequestDuplicated(request.getRequestId())) {
            log.error("The request is duplicated");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The request is duplicated");
        }
        return ResponseEntity.ok(gatewayService.getCurrentRates(request));
    }

    @PostMapping(value = "/history", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistoryRates(@RequestBody @Valid HistoryRequest request) {
        try {
            return ResponseEntity.ok(gatewayService.getHistoryRates(request));
        } catch (DuplicateRequestException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}