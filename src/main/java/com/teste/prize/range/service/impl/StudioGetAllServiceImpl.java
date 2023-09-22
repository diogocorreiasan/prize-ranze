package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.service.StudioGetAllService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudioGetAllServiceImpl implements StudioGetAllService {
    private final StudioAdapterRepository studioAdapterRepository;

    @Override
    public Page<Studio> execute(final Integer page, final Integer size) {
        return studioAdapterRepository.getAll(page, size);
    }
}
