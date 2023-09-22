package com.teste.prize.range.service;

import com.teste.prize.range.domain.Film;

import java.util.Optional;

public interface FilmSearchByIdService {
    Optional<Film> execute(final Long id);
}
