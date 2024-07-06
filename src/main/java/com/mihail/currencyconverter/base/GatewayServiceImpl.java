package com.mihail.currencyconverter.base;

import com.mihail.currencyconverter.currencystatistic.controller.request.HistoryRequest;
import com.mihail.currencyconverter.currencystatistic.controller.request.RateRequest;
import com.mihail.currencyconverter.currencystatistic.controller.request.XmlCommandRequest;
import com.mihail.currencyconverter.currencystatistic.controller.response.HistoryResponse;
import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.controller.response.XmlCommandResponse;
import com.mihail.currencyconverter.collector.service.RatesCollectorService;
import com.mihail.currencyconverter.currencystatistic.service.HistoryServiceImpl;
import com.mihail.currencyconverter.currencystatistic.service.StatisticsServiceImpl;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final RatesCollectorService ratesCollectorService;
    private final HistoryServiceImpl historyService;
    private final StatisticsServiceImpl statisticsService;

    @Override
    public RateResponse getCurrentRates(RateRequest request) throws DuplicateRequestException {
        if (statisticsService.isRequestDuplicated(request.getRequestId())) {
            throw new DuplicateRequestException("Duplicate request ID");
        }
        statisticsService.storeRequestStatistics("EXT_SERVICE_X", request.getRequestId(), request.getClient());
        return ratesCollectorService.getLatestRatesForCurrency(request.getCurrency());
    }

    @Override
    public HistoryResponse getHistoryRates(HistoryRequest request) throws DuplicateRequestException {
        if (historyService.isHistoryRequestDuplicated(request.getRequestId())) {
            throw new DuplicateRequestException("Duplicate request ID");
        }

        var rateHistories = ratesCollectorService.getHistoricalRates(request.getCurrency(), request.getPeriod());
        historyService.storeHistoryRequestStatistics("EXT_SERVICE_X", request.getRequestId(), request.getClient(), rateHistories);

        return HistoryResponse.builder()
                .requestId(request.getRequestId())
                .history(rateHistories)
                .build();
    }

    @Override
    public XmlCommandResponse handleXmlCommand(XmlCommandRequest request) throws DuplicateRequestException {
        var response = new XmlCommandResponse();
        response.setRequestId(request.getId());

        if (request.getGet() != null) {
            return getLatestRateResponse(request, response);
        } else if (request.getHistory() != null) {
            return getHistoryRateResponse(request, response);
        }
        response.setStatus("error");
        response.setMessage("Invalid request");
        return response;
    }

    private XmlCommandResponse getHistoryRateResponse(XmlCommandRequest request, XmlCommandResponse response) {
        var clientId = request.getHistory().getConsumer();
        var currency = request.getHistory().getCurrency();
        var period = request.getHistory().getPeriod();

        if (historyService.isHistoryRequestDuplicated(request.getId())) {
            response.setStatus("error");
            response.setMessage("Duplicate request ID");
            throw new DuplicateRequestException("Duplicate request ID");
        }

        var rateHistories = ratesCollectorService.getHistoricalRates(currency, period);
        historyService.storeHistoryRequestStatistics("EXT_SERVICE_Y", request.getId(), clientId, rateHistories);

        response.setStatus("success");
        response.setMessage("Historical rates for " + currency + " for the past " + period + " hours");
        response.setHistoryResponse(HistoryResponse.builder()
                .requestId(request.getId())
                .history(rateHistories)
                .build());
        return response;
    }

    private XmlCommandResponse getLatestRateResponse(XmlCommandRequest request, XmlCommandResponse response) {
        var clientId = request.getGet().getConsumer();
        var currency = request.getGet().getCurrency();

        if (statisticsService.isRequestDuplicated(request.getId())) {
            response.setStatus("error");
            response.setMessage("Duplicate request ID");
            throw new DuplicateRequestException("Duplicate request ID");
        }

        statisticsService.storeRequestStatistics("EXT_SERVICE_Y", request.getId(), clientId);
        var ratesResponse = ratesCollectorService.getLatestRatesForCurrency(currency);

        response.setStatus("success");
        response.setMessage("Current rate for " + currency);
        response.setRatesResponse(ratesResponse);
        return response;
    }
}