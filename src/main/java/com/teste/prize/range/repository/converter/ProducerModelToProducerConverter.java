package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.repository.model.ProducerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerModelToProducerConverter {
    private final ModelMapper modelMapper;

    public Producer convert(final ProducerModel producerModel){
        return modelMapper.map(producerModel, Producer.class);
    }
}
