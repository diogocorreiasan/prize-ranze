package com.teste.prize.range.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse {
    private Long id;
    private int year;
    private String title;
    private List<StudioResponse> studios;
    private List<ProducerResponse> producers;
    private String winner;
}
