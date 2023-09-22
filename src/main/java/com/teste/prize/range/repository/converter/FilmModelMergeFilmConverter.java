package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.repository.model.FilmModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmModelMergeFilmConverter {
    private final ModelMapper modelMapper;

    public FilmModel convert(final Long id, final Film film){
        final var filmModel =  modelMapper.map(film, FilmModel.class);
        return filmModel.withId(id);
    }
}
