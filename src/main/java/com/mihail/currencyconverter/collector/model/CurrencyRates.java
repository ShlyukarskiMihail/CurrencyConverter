package com.mihail.currencyconverter.collector.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NamedEntityGraph(
        name = "currency-rates",
        attributeNodes = {
                @NamedAttributeNode("ratesCollector"),
                @NamedAttributeNode("currencyCode"),
                @NamedAttributeNode("rateToEuro")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class CurrencyRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "rates_collector_id", nullable = false)
    private RatesCollector ratesCollector;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private BigDecimal rateToEuro;

    @Override
    public String toString() {
        return "CurrencyRates{" +
                "id=" + id +
                ", currencyCode='" + currencyCode + '\'' +
                ", rateToEuro=" + rateToEuro +
                '}';
    }
}