package com.mihail.currencyconverter.currencystatistic.controller.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mihail.currencyconverter.currencystatistic.model.RateHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "HistoryResponse")
public class HistoryResponse {

    @JacksonXmlProperty
    private String requestId;

    @JacksonXmlElementWrapper(localName = "history")
    @JacksonXmlProperty(localName = "RateHistory")
    private List<RateHistory> history;
}