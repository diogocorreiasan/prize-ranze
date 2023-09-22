package com.teste.prize.range.service;

import com.teste.prize.range.domain.Producer;

import java.util.Optional;

public interface ProducerSearchByIdService {
    Optional<Producer> execute(final Long id);
}
