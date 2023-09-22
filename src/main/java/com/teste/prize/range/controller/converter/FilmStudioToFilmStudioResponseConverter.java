package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.FilmStudioResponse;
import com.teste.prize.range.domain.FilmStudio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmStudioToFilmStudioResponseConverter {
    private final ModelMapper modelMapper;

    public FilmStudioResponse convert(final FilmStudio filmStudio) {
        return modelMapper.map(filmStudio, FilmStudioResponse.class);
    }
}
