package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.FilmProducer;
import com.teste.prize.range.repository.model.FilmProducerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmProducerModelToFilmProducerConverter {
    private final ModelMapper modelMapper;

    public FilmProducer convert(final FilmProducerModel filmProducerModel){
        return modelMapper.map(filmProducerModel, FilmProducer.class);
    }
}
