package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.ProducerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerToProducerModelConverter {
    private ModelMapper modelMapper;

    public ProducerModel convert(final Producer producer) {
        return modelMapper.map(producer, ProducerModel.class);
    }
}
