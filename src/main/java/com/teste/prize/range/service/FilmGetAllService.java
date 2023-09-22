package com.teste.prize.range.service;

import com.teste.prize.range.domain.Film;
import org.springframework.data.domain.Page;

public interface FilmGetAllService {
    Page<Film> execute(final Integer page, final Integer size);
}
