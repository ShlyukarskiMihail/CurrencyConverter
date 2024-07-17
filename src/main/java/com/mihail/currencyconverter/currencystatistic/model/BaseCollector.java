package com.mihail.currencyconverter.currencystatistic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseCollector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String serviceName;

    @Column(nullable = false, unique = true)
    private String requestId;

    private String clientId;

    @Override
    public String toString() {
        return "BaseCollector{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", requestId='" + requestId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}