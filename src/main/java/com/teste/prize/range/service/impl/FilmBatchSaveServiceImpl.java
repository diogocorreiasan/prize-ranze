package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.service.FilmBatchSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmBatchSaveServiceImpl implements FilmBatchSaveService {
    private final FilmAdapterRepository filmAdapterRepository;
    private final ProducerAdapterRepository producerAdapterRepository;
    private final StudioAdapterRepository studioAdapterRepository;

    @Override
    public void execute(final List<Film> films) {
        films.forEach(this::saveFilm);
    }

    private void saveFilm(final Film film) {
        final var response = filmAdapterRepository.save(film);

        film.getProducers()
                .forEach(producer -> producerAdapterRepository.update(producer, response.getId()));

        film.getStudios()
                .forEach(studio -> studioAdapterRepository.update(studio, response.getId()));
    }
}
