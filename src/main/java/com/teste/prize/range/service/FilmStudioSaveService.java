package com.teste.prize.range.service;

import com.teste.prize.range.domain.FilmStudio;

public interface FilmStudioSaveService {
    FilmStudio execute(final Long studioId, final Long filmId);
}
