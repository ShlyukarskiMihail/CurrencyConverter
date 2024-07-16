package com.mihail.currencyconverter.base;

import com.mihail.currencyconverter.currencystatistic.controller.request.HistoryRequest;
import com.mihail.currencyconverter.currencystatistic.controller.request.RateRequest;
import com.mihail.currencyconverter.currencystatistic.controller.request.XmlCommandRequest;
import com.mihail.currencyconverter.currencystatistic.controller.response.HistoryResponse;
import com.mihail.currencyconverter.currencystatistic.controller.response.RateResponse;
import com.mihail.currencyconverter.currencystatistic.controller.response.XmlCommandResponse;

public interface GatewayService {
    RateResponse getCurrentRates(final RateRequest request);

    HistoryResponse getHistoryRates(final HistoryRequest request);

    XmlCommandResponse handleXmlCommand(final XmlCommandRequest request);
}