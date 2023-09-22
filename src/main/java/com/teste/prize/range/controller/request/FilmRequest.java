package com.teste.prize.range.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmRequest {
    private Long id;
    private int year;
    private String title;
    private String winner;
}
