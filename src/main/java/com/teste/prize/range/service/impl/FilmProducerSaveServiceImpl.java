package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.domain.FilmProducer;
import com.teste.prize.range.service.FilmProducerSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmProducerSaveServiceImpl implements FilmProducerSaveService {
    private final FilmProducerAdapterRepository filmProducerAdapterRepository;

    @Override
    public FilmProducer execute(final Long producerId, final Long filmId) {
        return filmProducerAdapterRepository.addProducer(producerId, filmId);
    }
}
