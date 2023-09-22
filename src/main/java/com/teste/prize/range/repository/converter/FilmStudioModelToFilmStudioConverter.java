package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.FilmStudio;
import com.teste.prize.range.repository.model.FilmStudioModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmStudioModelToFilmStudioConverter {
    private final ModelMapper modelMapper;

    public FilmStudio convert(final FilmStudioModel filmStudioModel){
        return modelMapper.map(filmStudioModel, FilmStudio.class);
    }
}
