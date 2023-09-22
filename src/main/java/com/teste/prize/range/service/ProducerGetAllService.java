package com.teste.prize.range.service;

import com.teste.prize.range.domain.Producer;
import org.springframework.data.domain.Page;

public interface ProducerGetAllService {
    Page<Producer> execute(final Integer page, final Integer size);
}
