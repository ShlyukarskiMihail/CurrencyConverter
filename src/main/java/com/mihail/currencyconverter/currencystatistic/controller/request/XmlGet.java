package com.mihail.currencyconverter.currencystatistic.controller.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XmlGet {

    @JacksonXmlProperty(isAttribute = true)
    private String consumer;

    @JacksonXmlProperty
    private String currency;
}