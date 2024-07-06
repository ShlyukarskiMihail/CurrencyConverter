package com.mihail.currencyconverter.collector.service;

import com.mihail.currencyconverter.collector.controller.response.RatesCollectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Log4j2
public class FixerServiceImpl implements FixerService{

    @Value("${FIXER_URL}")
    private String apiUrl;

    @Value("${FIXER_KEY}")
    private String apiKey;
    private final WebClient.Builder webClientBuilder;

    @Override
    public RatesCollectorResponse getLatestRates() {
        log.info("Sending request to Fixer API: {}", getResource(apiUrl, apiKey));

        try {
            var response = buildResponse(getResource(apiUrl, apiKey));
            if (response == null || response.getBase() == null) {
                log.error("Fixer API returned null response or base currency is null");
            }
            return response;
        } catch (final WebClientException e) {
            throw new IllegalStateException("Error connecting to Fixer API: " + e.getMessage());
        }
    }

    private String getResource(final String apiUrl, final String apiKey) {
        return UriComponentsBuilder.fromUriString(apiUrl)
                .path("/latest")
                .queryParam("access_key", apiKey)
                .toUriString();
    }

    private RatesCollectorResponse buildResponse(final String url) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(RatesCollectorResponse.class)
                .block();
    }
}