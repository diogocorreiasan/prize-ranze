package com.teste.prize.range.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmStudioResponse {
    private Long id;
    private FilmResponse film;
    private StudioResponse studio;
}
