package com.mihail.currencyconverter.ratecollectormodule.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@NamedEntityGraph(
        name = "collector-graph",
        attributeNodes = {
                @NamedAttributeNode("timestamp"),
                @NamedAttributeNode("baseCurrency"),
                @NamedAttributeNode(value = "rateList", subgraph = "rates-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "rates-subgraph",
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
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private String baseCurrency;

    @OneToMany(mappedBy = "collector", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rateList = new ArrayList<>();

    @Override
    public String toString() {
        return "Rates Collector{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", baseCurrency='" + baseCurrency + '\'' +
                '}';
    }
}