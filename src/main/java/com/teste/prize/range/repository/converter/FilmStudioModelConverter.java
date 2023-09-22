package com.teste.prize.range.repository.converter;

import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.FilmStudioModel;
import com.teste.prize.range.repository.model.StudioModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmStudioModelConverter {

    public FilmStudioModel convert(final Long studioId, final Long filmId){
        return FilmStudioModel
                .builder()
                .studio(getStudio(studioId))
                .film(getFilm(filmId))
                .build();
    }

    private FilmModel getFilm(final Long filmId) {
        return FilmModel
                .builder()
                .id(filmId)
                .build();
    }

    private StudioModel getStudio(final Long studioId){
        return StudioModel
                .builder()
                .id(studioId)
                .build();
    }
}
