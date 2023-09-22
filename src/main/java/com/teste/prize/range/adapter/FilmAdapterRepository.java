package com.teste.prize.range.adapter;

import com.teste.prize.range.domain.Film;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface FilmAdapterRepository {
    Film save(final Film film);
    Page<Film> getAll(final Integer page, final Integer size);
    Film saveOrUpdate(final Film film);
    Optional<Film> findById(final Long id);
    void delete(final Long id);
}
