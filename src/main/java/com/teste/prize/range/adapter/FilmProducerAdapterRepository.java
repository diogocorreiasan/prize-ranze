package com.teste.prize.range.adapter;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.domain.FilmProducer;
import com.teste.prize.range.domain.Producer;

import java.util.List;

public interface FilmProducerAdapterRepository {
    List<Film> findByProducer(final Long id, final String winner);
    List<Producer> findByFilmId(final Long id);
    void delete(final Long producer, final Long film);
    FilmProducer addProducer(final Long producerId, final Long filmId);
}
