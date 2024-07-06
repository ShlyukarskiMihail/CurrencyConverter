package com.mihail.currencyconverter.collector.controller.api;

import com.mihail.currencyconverter.collector.dto.RatesCollectorDTO;
import com.mihail.currencyconverter.collector.service.RatesCollectorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RatesCollectorController {

    private final RatesCollectorServiceImpl service;

    @PostMapping("/updateRates")
    public ResponseEntity<RatesCollectorDTO> updateRatesManually() {
        return ResponseEntity.ok(service.updateRatesData());
    }
}