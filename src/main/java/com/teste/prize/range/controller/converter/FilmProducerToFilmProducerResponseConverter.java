package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.FilmProducerResponse;
import com.teste.prize.range.domain.FilmProducer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmProducerToFilmProducerResponseConverter {
    private final ModelMapper modelMapper;

    public FilmProducerResponse convert(final FilmProducer filmProducer) {
        return modelMapper.map(filmProducer, FilmProducerResponse.class);
    }
}
