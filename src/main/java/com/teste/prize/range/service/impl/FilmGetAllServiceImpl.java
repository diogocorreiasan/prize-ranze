package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.adapter.FilmStudioAdapterRepository;
import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.service.FilmGetAllService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
public class FilmGetAllServiceImpl implements FilmGetAllService {

    private final FilmAdapterRepository filmAdapterRepository;
    private final FilmProducerAdapterRepository filmProducerAdapterRepository;
    private final FilmStudioAdapterRepository filmStudioAdapterRepository;

    @Override
    public Page<Film> execute(final Integer page, final Integer size) {
        return filmAdapterRepository.getAll(page, size)
                .map(this::getProducer)
                .map(this::getStudio);
    }

    private Film getStudio(final Film film) {
        return of(filmStudioAdapterRepository.findByFilmId(film.getId()))
                .map(film::withStudios)
                .orElseGet(() -> film);
    }

    private Film getProducer(final Film film) {
        return of(filmProducerAdapterRepository.findByFilmId(film.getId()))
                .map(film::withProducers)
                .orElseGet(() -> film);
    }
}
