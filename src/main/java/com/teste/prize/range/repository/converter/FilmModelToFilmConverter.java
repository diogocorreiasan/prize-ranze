package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.repository.model.FilmModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmModelToFilmConverter {
    private final ModelMapper modelMapper;
    public Film convert(final FilmModel filmModel){
        return modelMapper.map(filmModel, Film.class);
    }

}
