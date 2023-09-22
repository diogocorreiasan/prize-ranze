package com.teste.prize.range.repository;

import com.teste.prize.range.repository.model.FilmModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmModel, Long> {
}
