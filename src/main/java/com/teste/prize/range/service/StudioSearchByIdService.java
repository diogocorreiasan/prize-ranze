package com.teste.prize.range.service;

import com.teste.prize.range.domain.Studio;

import java.util.Optional;

public interface StudioSearchByIdService {
    Optional<Studio> execute(final Long id);
}
