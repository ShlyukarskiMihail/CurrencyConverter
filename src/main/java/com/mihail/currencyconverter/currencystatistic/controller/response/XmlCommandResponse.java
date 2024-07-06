package com.mihail.currencyconverter.currencystatistic.controller.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "response")
public class XmlCommandResponse {

    @JacksonXmlProperty(localName = "id")
    private String requestId;

    @JacksonXmlProperty(localName = "status")
    private String status;

    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "RatesResponse")
    private RateResponse ratesResponse;

    @JacksonXmlProperty(localName = "HistoryResponse")
    private HistoryResponse historyResponse;
}