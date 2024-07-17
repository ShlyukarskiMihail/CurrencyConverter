package com.mihail.currencyconverter.currencystatistic.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Setter(AccessLevel.PUBLIC)
@NamedEntityGraph(
        name = "history-collector-graph",
        attributeNodes = {
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
public class HistoryCollector extends BaseCollector {

    @OneToMany(mappedBy = "historyCollector", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RateHistory> rateHistory = new ArrayList<>();

    @Override
    public String toString() {
        return "HistoryCollector{" +
                "id=" + getId() +
                ", serviceName='" + getServiceName() + '\'' +
                ", requestId='" + getRequestId() + '\'' +
                ", clientId='" + getClientId() + '\'' +
                ", rateHistory=" + rateHistory +
                '}';
    }
}