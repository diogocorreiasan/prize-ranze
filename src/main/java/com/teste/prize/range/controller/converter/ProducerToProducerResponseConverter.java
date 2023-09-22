package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.ProducerResponse;
import com.teste.prize.range.domain.Producer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerToProducerResponseConverter {
    private final ModelMapper modelMapper;

    public ProducerResponse convert(final Producer producer) {
        return modelMapper.map(producer, ProducerResponse.class);
    }
}
