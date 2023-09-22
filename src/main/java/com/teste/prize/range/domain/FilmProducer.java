package com.teste.prize.range.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmProducer {
    private Long id;
    private Film film;
    private Producer producer;
}
