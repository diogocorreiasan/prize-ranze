package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.adapter.FilmStudioAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.service.FilmSearchByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
public class FilmSearchByIdServiceImpl implements FilmSearchByIdService {
    private final FilmAdapterRepository filmAdapterRepository;
    private final FilmProducerAdapterRepository filmProducerAdapterRepository;
    private final FilmStudioAdapterRepository filmStudioAdapterRepository;

    @Override
    public Optional<Film> execute(final Long id) {
        return filmAdapterRepository.findById(id)
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
