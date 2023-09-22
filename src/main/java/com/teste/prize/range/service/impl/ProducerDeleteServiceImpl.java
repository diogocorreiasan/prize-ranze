package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.service.ProducerDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerDeleteServiceImpl implements ProducerDeleteService {
    private final ProducerAdapterRepository producerAdapterRepository;

    @Override
    public void execute(final Long id) {
        producerAdapterRepository.delete(id);
    }
}
