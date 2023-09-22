package com.teste.prize.range.repository.converter;

import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.FilmProducerModel;
import com.teste.prize.range.repository.model.ProducerModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerModelToFilmProducerModelConverter {

    public FilmProducerModel convert(final ProducerModel producerModel, final Long id){
        return FilmProducerModel
                .builder()
                .film(FilmModel.builder().id(id).build())
                .producer(producerModel)
                .build();
    }
}
