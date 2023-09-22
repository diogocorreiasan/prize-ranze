package com.teste.prize.range.repository;

import com.teste.prize.range.repository.model.FilmStudioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmStudioRepository extends JpaRepository<FilmStudioModel, Long> {
    List<FilmStudioModel> findByFilmId(final Long id);

    Optional<FilmStudioModel> findByStudioIdAndFilmId(final Long studio, final Long film);
}
