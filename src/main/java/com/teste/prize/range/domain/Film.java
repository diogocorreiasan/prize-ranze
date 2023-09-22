package com.teste.prize.range.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Long id;
    private int year;
    private String title;
    @With
    private List<Studio> studios;
    @With
    private List<Producer> producers;
    private String winner;
}
