package com.teste.prize.range.adapter.impl;

import com.teste.prize.range.adapter.FilmStudioAdapterRepository;
import com.teste.prize.range.domain.FilmStudio;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.repository.FilmStudioRepository;
import com.teste.prize.range.repository.converter.FilmStudioModelConverter;
import com.teste.prize.range.repository.converter.FilmStudioModelToFilmStudioConverter;
import com.teste.prize.range.repository.converter.StudioModelToStudioConverter;
import com.teste.prize.range.repository.model.FilmStudioModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class FilmStudioAdapterRepositoryImpl implements FilmStudioAdapterRepository {

    private final FilmStudioRepository filmStudioRepository;
    private final FilmStudioModelConverter filmStudioModelConverter;
    private final StudioModelToStudioConverter studioModelToStudioConverter;
    private final FilmStudioModelToFilmStudioConverter filmStudioModelToFilmStudioConverter;

    @Override
    public List<Studio> findByFilmId(Long id) {
        return filmStudioRepository.findByFilmId(id)
                .stream()
                .map(FilmStudioModel::getStudio)
                .map(studioModelToStudioConverter::convert)
                .toList();
    }

    @Override
    public void delete(final Long studio, final Long film) {
        final var response = filmStudioRepository.findByStudioIdAndFilmId(studio, film)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            filmStudioRepository.deleteById(response.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public FilmStudio addStudio(final Long studioId, final Long filmId) {
        final var model = filmStudioModelConverter.convert(studioId, filmId);
        final var response = filmStudioRepository.save(model);
        return filmStudioModelToFilmStudioConverter.convert(response);
    }
}
