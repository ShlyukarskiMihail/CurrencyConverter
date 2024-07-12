package com.mihail.currencyconverter.ratecollectormodule.repository;

import com.example.ratecollectormodule.model.Collector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CollectorRepository extends JpaRepository<Collector, Long> {

    @EntityGraph("collector-graph")
    Optional<Collector> findTopByOrderByTimestampDesc();

    @EntityGraph("collector-graph")
    List<Collector> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}