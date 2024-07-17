package com.mihail.currencyconverter.currencystatistic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "rate-history",
        attributeNodes = {
                @NamedAttributeNode("currency"),
                @NamedAttributeNode("rate"),
                @NamedAttributeNode("historyCollector")
        })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class HistoricalRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String currency;

    @NotNull
    private BigDecimal rate;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "history_collector_id")
    private CurrencyHistory historyCollector;

    @Override
    public String toString() {
        return "RateHistory{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", timestamp=" + timestamp +
                '}';
    }
}