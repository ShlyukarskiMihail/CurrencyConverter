package com.mihail.currencyconverter.currencystatistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd' T 'HH:mm:ss")
    private LocalDateTime timestamp;

    private String clientId;
}