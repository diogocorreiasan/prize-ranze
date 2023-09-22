package com.teste.prize.range.repository;

import com.teste.prize.range.repository.model.StudioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudioRepository extends JpaRepository<StudioModel, Long> {
    Optional<StudioModel> findByName(final String name);
}
