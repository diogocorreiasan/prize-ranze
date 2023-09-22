package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.service.StudioSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudioSaveServiceImpl implements StudioSaveService {
    private final StudioAdapterRepository studioAdapterRepository;

    @Override
    public Studio execute(final Studio studio) {
        return studioAdapterRepository.saveOrUpdate(studio);
    }
}
