package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.service.FilmUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmUpdateServiceImpl implements FilmUpdateService {
    private final FilmAdapterRepository filmAdapterRepository;

    @Override
    public Film execute(final Film film) {
        return filmAdapterRepository.saveOrUpdate(film);
    }
}
