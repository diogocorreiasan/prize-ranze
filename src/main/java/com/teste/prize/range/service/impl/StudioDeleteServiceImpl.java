package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.service.StudioDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudioDeleteServiceImpl implements StudioDeleteService {
    private final StudioAdapterRepository studioAdapterRepository;

    @Override
    public void execute(final Long id) {
        studioAdapterRepository.delete(id);
    }
}
