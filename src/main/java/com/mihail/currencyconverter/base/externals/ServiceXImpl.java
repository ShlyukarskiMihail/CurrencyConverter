package com.mihail.currencyconverter.base.externals;

import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ServiceXImpl implements Service_EXT {

    private final WebClient.Builder webClientBuilder;

    @Value("${external.service1.url}")
    private String externalServiceUrl;

    public Mono<?> callExternalService(String parameter) {
        return webClientBuilder.build()
                .get()
                .uri(externalServiceUrl + "/endpoint?param=" + parameter)
                .retrieve()
                .bodyToMono(RateResponse.class);
    }
}