package com.teste.prize.range.adapter.impl;

import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.domain.FilmProducer;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.repository.FilmProducerRepository;
import com.teste.prize.range.repository.converter.FilmModelToFilmConverter;
import com.teste.prize.range.repository.converter.FilmProducerModelConverter;
import com.teste.prize.range.repository.converter.FilmProducerModelToFilmProducerConverter;
import com.teste.prize.range.repository.converter.ProducerModelToProducerConverter;
import com.teste.prize.range.repository.model.FilmProducerModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class FilmProducerAdapterRepositoryImpl implements FilmProducerAdapterRepository {

    private final FilmProducerRepository filmProducerRepository;
    private final FilmModelToFilmConverter filmModelToFilmConverter;
    private final FilmProducerModelConverter filmProducerModelConverter;
    private final ProducerModelToProducerConverter producerModelToProducerConverter;
    private final FilmProducerModelToFilmProducerConverter filmProducerModelToFilmProducerConverter;
    @Override
    public List<Film> findByProducer(final Long id, final String winner) {
        return filmProducerRepository.findByProducerIdAndFilmWinner(id, winner)
                .stream()
                .map(FilmProducerModel::getFilm)
                .map(filmModelToFilmConverter::convert)
                .toList();
    }

    @Override
    public List<Producer> findByFilmId(final Long id) {
        return filmProducerRepository.findByFilmId(id)
                .stream()
                .map(FilmProducerModel::getProducer)
                .map(producerModelToProducerConverter::convert)
                .toList();
    }

    @Override
    public void delete(final Long producer, final Long film) {
        final var response = filmProducerRepository.findByProducerIdAndFilmId(producer, film)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            filmProducerRepository.deleteById(response.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public FilmProducer addProducer(final Long producerId, final Long filmId) {
        final var model = filmProducerModelConverter.convert(producerId, filmId);
        final var response = filmProducerRepository.save(model);
        return filmProducerModelToFilmProducerConverter.convert(response);
    }
}
