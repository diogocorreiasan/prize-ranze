package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmAdapterRepository;
import com.teste.prize.range.service.FilmDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmDeleteServiceImpl implements FilmDeleteService {
    private final FilmAdapterRepository filmAdapterRepository;

    @Override
    public void execute(final Long id) {
        filmAdapterRepository.delete(id);
    }
}
