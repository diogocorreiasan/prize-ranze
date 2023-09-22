package com.teste.prize.range.adapter;

import com.teste.prize.range.domain.Studio;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudioAdapterRepository {
    void update(final Studio studio, final Long id);
    Page<Studio> getAll(final Integer page, final Integer size);
    Studio saveOrUpdate(final Studio studio);
    Optional<Studio> findById(final Long id);
    void delete(final Long id);
}
