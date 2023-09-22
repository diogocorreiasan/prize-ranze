package com.teste.prize.range.service;

import com.teste.prize.range.domain.FilmProducer;

public interface FilmProducerSaveService {
    FilmProducer execute(final Long producerId, final Long filmId);
}
