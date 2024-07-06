package com.mihail.currencyconverter.currencystatistic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@NamedEntityGraph(
        name = "history-collector-graph",
        attributeNodes = {
                @NamedAttributeNode("serviceName"),
                @NamedAttributeNode("requestId"),
                @NamedAttributeNode("clientId"),
                @NamedAttributeNode(value = "rateHistory", subgraph = "rate-history")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "rate-history",
                        attributeNodes = {
                                @NamedAttributeNode("currency"),
                                @NamedAttributeNode("rate"),
                                @NamedAttributeNode("timestamp"),
                                @NamedAttributeNode("historyCollector")
                        })})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class HistoryCollector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    @Column(nullable = false, unique = true)
    private String requestId;

    private String clientId;

    @OneToMany(mappedBy = "historyCollector", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RateHistory> rateHistory = new ArrayList<>();

    @Override
    public String toString() {
        return "HistoryCollector{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", requestId='" + requestId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}