package com.mihail.currencyconverter.currencystatistic.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "statistic-collector",
        attributeNodes = {
                @NamedAttributeNode("serviceName"),
                @NamedAttributeNode("requestId"),
                @NamedAttributeNode("clientId")
        })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class StatisticsCollector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    @Column(nullable = false, unique = true)
    private String requestId;

    private LocalDateTime timestamp;

    private String clientId;
}