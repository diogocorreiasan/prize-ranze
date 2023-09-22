package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.service.ProducerSearchByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProducerSearchByIdServiceImpl implements ProducerSearchByIdService {
    private final ProducerAdapterRepository producerAdapterRepository;

    @Override
    public Optional<Producer> execute(final Long id) {
        return producerAdapterRepository.findById(id);
    }
}
