package com.teste.prize.range.service;

import com.teste.prize.range.domain.Film;

import java.util.List;

public interface FilmBatchSaveService {
    void execute(final List<Film> film);
}
