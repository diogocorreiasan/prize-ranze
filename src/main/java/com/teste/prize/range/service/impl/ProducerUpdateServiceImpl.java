package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.service.ProducerUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerUpdateServiceImpl implements ProducerUpdateService {
    private final ProducerAdapterRepository producerAdapterRepository;

    @Override
    public Producer execute(final Producer producer) {
        return producerAdapterRepository.saveOrUpdate(producer);
    }
}
