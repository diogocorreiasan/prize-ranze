package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmStudioAdapterRepository;
import com.teste.prize.range.service.FilmStudioDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmStudioDeleteServiceImpl implements FilmStudioDeleteService {
    private final FilmStudioAdapterRepository filmStudioAdapterRepository;

    @Override
    public void execute(final Long studio, final Long film) {
        filmStudioAdapterRepository.delete(studio, film);
    }
}
