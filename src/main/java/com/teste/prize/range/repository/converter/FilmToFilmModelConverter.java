package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class FilmToFilmModelConverter {

    public FilmModel convert(final Film film){
        return FilmModel
                .builder()
                .id(film.getId())
                .title(film.getTitle())
                .winner(getWinner(film.getWinner()))
                .year(film.getYear())
                .build();
    }

    private String getWinner(final String winnerRequest) {
        return ofNullable(winnerRequest)
                .map(winner -> winner.replace(Constants.REGEX, Constants.EMPTY))
                .orElseGet(() -> null);
    }

}
