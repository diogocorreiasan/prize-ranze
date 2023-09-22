package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.service.FilmProducerDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmProducerDeleteServiceImpl implements FilmProducerDeleteService {
    private final FilmProducerAdapterRepository filmProducerAdapterRepository;

    @Override
    public void execute(final Long producer, final Long filmId) {
        filmProducerAdapterRepository.delete(producer, filmId);
    }
}
