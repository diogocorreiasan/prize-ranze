package com.teste.prize.range.adapter;

import com.teste.prize.range.domain.FilmStudio;
import com.teste.prize.range.domain.Studio;

import java.util.List;

public interface FilmStudioAdapterRepository {
    List<Studio> findByFilmId(final Long id);

    void delete(final Long studio, final Long film);
    FilmStudio addStudio(final Long studioId, final Long filmId);
}
