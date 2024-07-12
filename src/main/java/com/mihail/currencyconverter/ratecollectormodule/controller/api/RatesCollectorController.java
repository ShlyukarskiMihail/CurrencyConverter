package com.mihail.currencyconverter.ratecollectormodule.controller.api;

import com.mihail.currencyconverter.ratecollectormodule.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class RatesCollectorController {

    private final CollectorService collectorService;

    @PostMapping("/currency-rates")
    public ResponseEntity<?> updateRates() {
        return ResponseEntity.ok(collectorService.getRatesData());
    }
}
