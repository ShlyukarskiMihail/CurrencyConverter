package com.mihail.currencyconverter.ratecollectormodule.service;

import com.example.ratecollectormodule.controller.response.CollectorResponse;
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
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${FIXER_URL}")
    private String apiUrl;

    @Value("${FIXER_KEY}")
    private String apiKey;

    private final WebClient.Builder webClientBuilder;

    @Override
    public CollectorResponse getLatestRates() {
        log.info("Sending request to Fixer API: {}", getResource(apiUrl, apiKey));

        try {
            final CollectorResponse response = buildResponse(getResource(apiUrl, apiKey));

            if (response == null) {
                log.error("Fixer API returned null response");
                throw new IllegalStateException("Fixer API returned null response");
            }

            if (response.getBase() == null) {
                log.error("Fixer API returned response with null base currency");
                throw new IllegalStateException("Fixer API returned response with null base currency");
            }

            return response;
        } catch (WebClientException e) {
            log.error("Error connecting to Fixer API: {}", e.getMessage());
            throw new IllegalStateException("Error connecting to Fixer API", e);
        }
    }

    private String getResource(final String apiUrl, final String apiKey) {
        return UriComponentsBuilder.fromUriString(apiUrl)
                .path("/latest")
                .queryParam("access_key", apiKey)
                .toUriString();
    }

    private CollectorResponse buildResponse(final String url) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(CollectorResponse.class)
                .block();
    }
}