package com.teste.prize.range.adapter.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.repository.FilmRepository;
import com.teste.prize.range.repository.converter.FilmModelMergeFilmConverter;
import com.teste.prize.range.repository.converter.FilmModelToFilmConverter;
import com.teste.prize.range.repository.converter.FilmToFilmModelConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class FilmAdapterRepositoryImpl implements FilmAdapterRepository {

    private final FilmRepository filmRepository;
    private final FilmModelToFilmConverter filmModelToFilmConverter;
    private final FilmToFilmModelConverter filmToFilmModelConverter;
    private final FilmModelMergeFilmConverter filmModelMergeFilmConverter;

    @Override
    public Page<Film> getAll(final Integer page, final Integer size) {
        return filmRepository.findAll(PageRequest.of(page, size))
                .map(filmModelToFilmConverter::convert);
    }

    @Override
    public Film saveOrUpdate(final Film film) {
        return ofNullable(film.getId())
                .flatMap(filmRepository::findById)
                .map(filmModel -> filmModelMergeFilmConverter.convert(filmModel.getId(), film))
                .map(filmRepository::save)
                .map(filmModelToFilmConverter::convert)
                .orElseGet(() -> save(film));
    }

    @Override
    public Film save(final Film film) {
        final var filmModel = filmToFilmModelConverter.convert(film);
        final var filmSave = filmRepository.save(filmModel);
        return filmModelToFilmConverter.convert(filmSave);
    }

    @Override
    public Optional<Film> findById(final Long id) {
        return filmRepository.findById(id)
                .map(filmModelToFilmConverter::convert);
    }

    @Override
    public void delete(final Long id) {
        final var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        filmRepository.deleteById(film.getId());
    }
}
