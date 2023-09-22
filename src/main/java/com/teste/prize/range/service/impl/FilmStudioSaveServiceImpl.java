package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmStudioAdapterRepository;
import com.teste.prize.range.domain.FilmStudio;
import com.teste.prize.range.service.FilmStudioSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmStudioSaveServiceImpl implements FilmStudioSaveService {
    private final FilmStudioAdapterRepository filmStudioAdapterRepository;
    @Override
    public FilmStudio execute(final Long studioId, final Long filmId) {
        return filmStudioAdapterRepository.addStudio(studioId, filmId);
    }
}
