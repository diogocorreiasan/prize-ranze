package com.teste.prize.range.adapter.impl;

import com.teste.prize.range.adapter.StudioAdapterRepository;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.repository.FilmStudioRepository;
import com.teste.prize.range.repository.StudioRepository;
import com.teste.prize.range.repository.converter.StudioModelMergeStudioConverter;
import com.teste.prize.range.repository.converter.StudioModelToFilmStudioModelConverter;
import com.teste.prize.range.repository.converter.StudioModelToStudioConverter;
import com.teste.prize.range.repository.converter.StudioToStudioModelConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class StudioAdapterRepositoryImpl implements StudioAdapterRepository {
    private final StudioRepository studioRepository;
    private final FilmStudioRepository filmStudioRepository;
    private final StudioToStudioModelConverter studioToStudioModelConverter;
    private final StudioModelToStudioConverter studioModelToStudioConverter;
    private final StudioModelMergeStudioConverter studioModelMergeStudioConverter;
    private final StudioModelToFilmStudioModelConverter studioModelToFilmStudioModelConverter;

    @Override
    public void update(final Studio studio, final Long id) {
        final var studioModel = studioRepository.findByName(studio.getName())
                .orElseGet(() -> null);

        if(nonNull(studioModel)) {
            final var filmStudioModel = studioModelToFilmStudioModelConverter.convert(studioModel, id);
            filmStudioRepository.save(filmStudioModel);
        }else {
            saveBatch(studio, id);
        }
    }

    @Override
    public Page<Studio> getAll(final Integer page, final Integer size) {
        return studioRepository.findAll(PageRequest.of(page, size))
                .map(studioModelToStudioConverter::convert);
    }

    private void saveBatch(final Studio studio, final Long id){
        Optional.of(studioToStudioModelConverter.convert(studio))
                .map(studioRepository::save)
                .map(producerModel -> studioModelToFilmStudioModelConverter.convert(producerModel, id))
                .map(filmStudioRepository::save);
    }

    @Override
    public Studio saveOrUpdate(final Studio studio) {
        return ofNullable(studio.getId())
                .flatMap(studioRepository::findById)
                .map(studioModel -> studioModelMergeStudioConverter.convert(studioModel.getId(), studio))
                .map(studioRepository::save)
                .map(studioModelToStudioConverter::convert)
                .orElseGet(() -> save(studio));
    }

    private Studio save(final Studio studio) {
        final var studioModel = studioToStudioModelConverter.convert(studio);
        final var studioSave = studioRepository.save(studioModel);
        return studioModelToStudioConverter.convert(studioSave);
    }

    @Override
    public Optional<Studio> findById(final Long id) {
        return studioRepository.findById(id)
                .map(studioModelToStudioConverter::convert);
    }

    @Override
    public void delete(final Long id) {
        final var response = studioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            studioRepository.deleteById(response.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }
}
