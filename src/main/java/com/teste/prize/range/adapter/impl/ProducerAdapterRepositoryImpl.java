package com.teste.prize.range.adapter.impl;

import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.repository.FilmProducerRepository;
import com.teste.prize.range.repository.ProducerRepository;
import com.teste.prize.range.repository.converter.ProducerModelMergeProducerConverter;
import com.teste.prize.range.repository.converter.ProducerModelToFilmProducerModelConverter;
import com.teste.prize.range.repository.converter.ProducerModelToProducerConverter;
import com.teste.prize.range.repository.converter.ProducerToProducerModelConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class ProducerAdapterRepositoryImpl implements ProducerAdapterRepository {

    private final ProducerRepository producerRepository;
    private final FilmProducerRepository filmProducerRepository;
    private final ProducerToProducerModelConverter producerToProducerModelConverter;
    private final ProducerModelToProducerConverter producerModelToProducerConverter;
    private final ProducerModelMergeProducerConverter producerModelMergeProducerConverter;
    private final ProducerModelToFilmProducerModelConverter producerModelToFilmProducerModelConverter;

    @Override
    public void update(final Producer producer, final Long id) {
         final var producerModel = producerRepository.findByName(producer.getName())
                 .orElseGet(() -> null);

         if(nonNull(producerModel)) {
             final var filmProducer = producerModelToFilmProducerModelConverter.convert(producerModel, id);
             filmProducerRepository.save(filmProducer);
         }else {
             saveBatch(producer, id);
         }
    }

    private void saveBatch(final Producer producer, final Long id){
        of(producerToProducerModelConverter.convert(producer))
                .map(producerRepository::save)
                .map(producerModel -> producerModelToFilmProducerModelConverter.convert(producerModel, id))
                .map(filmProducerRepository::save);
    }

    @Override
    public Page<Producer> getAll(final Integer page, final Integer size) {
        return producerRepository.findAll(PageRequest.of(page, size))
                .map(producerModelToProducerConverter::convert);
    }

    @Override
    public List<Producer> getAll() {
        return producerRepository.findAll()
                .stream()
                .map(producerModelToProducerConverter::convert)
                .toList();
    }

    @Override
    public Producer saveOrUpdate(final Producer producer) {
        return ofNullable(producer.getId())
                .flatMap(producerRepository::findById)
                .map(producerModel -> producerModelMergeProducerConverter.convert(producerModel.getId(), producer))
                .map(producerRepository::save)
                .map(producerModelToProducerConverter::convert)
                .orElseGet(() -> save(producer));
    }


    private Producer save(final Producer producer) {
        final var producerModel = producerToProducerModelConverter.convert(producer);
        final var producerSave = producerRepository.save(producerModel);
        return producerModelToProducerConverter.convert(producerSave);
    }

    @Override
    public Optional<Producer> findById(final Long id) {
        return producerRepository.findById(id)
                .map(producerModelToProducerConverter::convert);
    }

    @Override
    public void delete(final Long id) {
        final var response = producerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            producerRepository.deleteById(response.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

    }
}
