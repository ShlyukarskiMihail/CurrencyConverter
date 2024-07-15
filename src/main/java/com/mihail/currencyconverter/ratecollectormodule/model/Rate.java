package com.mihail.currencyconverter.ratecollectormodule.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NamedEntityGraph(
        name = "rates",
        attributeNodes = {
                @NamedAttributeNode("collector"),
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
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "collector_id", nullable = false)
    private Collector collector;

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