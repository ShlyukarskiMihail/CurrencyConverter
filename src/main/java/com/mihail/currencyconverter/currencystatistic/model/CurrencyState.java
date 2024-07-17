package com.mihail.currencyconverter.currencystatistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class CurrencyState extends StatisticCollector {

    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Statistic Collector: {" +
                "id=" + getId() +
                ", serviceName='" + getServiceName() + '\'' +
                ", requestId='" + getRequestId() + '\'' +
                ", clientId='" + getClientId() + '\'' +
                ", timestamp=" + this.timestamp +
                '}';
    }
}