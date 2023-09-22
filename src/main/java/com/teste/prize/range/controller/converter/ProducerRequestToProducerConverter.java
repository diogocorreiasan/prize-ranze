package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.request.ProducerRequest;
import com.teste.prize.range.domain.Producer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerRequestToProducerConverter {
    private final ModelMapper modelMapper;

    public Producer convert(final ProducerRequest producerRequest) {
        return modelMapper.map(producerRequest, Producer.class);
    }
}
