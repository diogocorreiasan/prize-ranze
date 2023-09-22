package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.request.FilmRequest;
import com.teste.prize.range.domain.Film;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmRequestToFilmConverter {

    private final ModelMapper modelMapper;

    public Film convert(final FilmRequest filmRequest){
        return modelMapper.map(filmRequest, Film.class);
    }
}
