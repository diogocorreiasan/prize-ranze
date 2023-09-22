package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.ProducerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerModelMergeProducerConverter {
    private final ModelMapper modelMapper;

    public ProducerModel convert(final Long id, final Producer producer){
        final var model =  modelMapper.map(producer, ProducerModel.class);
        return model.withId(id);
    }
}
