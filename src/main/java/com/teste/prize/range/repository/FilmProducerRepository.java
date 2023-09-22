package com.teste.prize.range.repository;

import com.teste.prize.range.repository.model.FilmProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmProducerRepository extends JpaRepository<FilmProducerModel, Long> {
    List<FilmProducerModel> findByProducerIdAndFilmWinner(final Long id, final String winne);

    List<FilmProducerModel> findByFilmId(final Long id);

    Optional<FilmProducerModel> findByProducerIdAndFilmId(final Long producer, final Long film);
}
