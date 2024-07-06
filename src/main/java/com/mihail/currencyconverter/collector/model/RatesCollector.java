package com.mihail.currencyconverter.collector.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@NamedEntityGraph(
        name = "rates-collector-graph",
        attributeNodes = {
                @NamedAttributeNode("timestamp"),
                @NamedAttributeNode("baseCurrency"),
                @NamedAttributeNode(value = "currencyRatesList", subgraph = "rates-list-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "rates-list-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("currencyCode"),
                                @NamedAttributeNode("rateToEuro")
                        }
                )
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class RatesCollector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private String baseCurrency;

    @OneToMany(mappedBy = "ratesCollector", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrencyRates> currencyRatesList = new ArrayList<>();

    @Override
    public String toString() {
        return "RatesCollector{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", baseCurrency='" + baseCurrency + '\'' +
                '}';
    }
}