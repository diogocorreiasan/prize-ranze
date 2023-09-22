package com.teste.prize.range.repository.converter;

import com.teste.prize.range.repository.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudioModelToFilmStudioModelConverter {

    public FilmStudioModel convert(final StudioModel studioModel, final Long id){
        return FilmStudioModel
                .builder()
                .film(FilmModel.builder().id(id).build())
                .studio(studioModel)
                .build();
    }
}
