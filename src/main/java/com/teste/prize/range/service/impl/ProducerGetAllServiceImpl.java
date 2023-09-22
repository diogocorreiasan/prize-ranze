package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.service.ProducerGetAllService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerGetAllServiceImpl implements ProducerGetAllService {
    private final ProducerAdapterRepository producerAdapterRepository;

    @Override
    public Page<Producer> execute(final Integer page, final Integer size) {
        return producerAdapterRepository.getAll(page, size);
    }
}
