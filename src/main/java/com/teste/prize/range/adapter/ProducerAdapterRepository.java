package com.teste.prize.range.adapter;

import com.teste.prize.range.domain.Producer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProducerAdapterRepository {
    void update(final Producer producer, final Long id);
    Page<Producer> getAll(final Integer page, final Integer size);
    List<Producer> getAll();
    Producer saveOrUpdate(final Producer producer);
    Optional<Producer> findById(final Long id);
    void delete(final Long id);
}
