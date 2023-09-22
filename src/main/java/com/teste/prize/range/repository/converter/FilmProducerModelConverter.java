package com.teste.prize.range.repository.converter;

import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.FilmProducerModel;
import com.teste.prize.range.repository.model.ProducerModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmProducerModelConverter {

    public FilmProducerModel convert(final Long producerId, final Long filmId) {
        return FilmProducerModel
                .builder()
                .producer(getProducer(producerId))
                .film(getStudio(filmId))
                .build();
    }

    private static FilmModel getStudio(final Long filmId) {
        return FilmModel
                .builder()
                .id(filmId)
                .build();
    }

    private static ProducerModel getProducer(final Long producerId) {
        return ProducerModel
                .builder()
                .id(producerId)
                .build();
    }
}
