package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.FilmResponse;
import com.teste.prize.range.domain.Film;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmToFilmResponseConverter {

    private final ModelMapper modelMapper;

    public FilmResponse convert(final Film film){
        return modelMapper.map(film, FilmResponse.class);
    }
}
