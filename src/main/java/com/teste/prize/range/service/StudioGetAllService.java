package com.teste.prize.range.service;

import com.teste.prize.range.domain.Studio;
import org.springframework.data.domain.Page;

public interface StudioGetAllService {
    Page<Studio> execute(final Integer page, final Integer size);
}
