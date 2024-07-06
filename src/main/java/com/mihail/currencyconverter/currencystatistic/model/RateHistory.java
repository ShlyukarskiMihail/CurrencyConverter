package com.mihail.currencyconverter.currencystatistic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class RateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private BigDecimal rate;
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "history_collector_id")
    private HistoryCollector historyCollector;

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