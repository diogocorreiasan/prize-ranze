package com.teste.prize.range.repository;

import com.teste.prize.range.repository.model.ProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<ProducerModel, Long> {
    Optional<ProducerModel> findByName(final String name);
}
