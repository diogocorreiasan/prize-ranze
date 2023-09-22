package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.service.StudioSearchByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudioSearchByIdServiceImpl implements StudioSearchByIdService {
    private final StudioAdapterRepository studioAdapterRepository;

    @Override
    public Optional<Studio> execute(final Long id) {
        return studioAdapterRepository.findById(id);
    }
}
