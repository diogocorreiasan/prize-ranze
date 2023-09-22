package com.teste.prize.range.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="tab_film")
public class FilmModel {
    @Id
    @With
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String title;
    @OneToMany(cascade= {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "film")
    @JsonManagedReference
    private List<FilmStudioModel> filmStudios;
    @OneToMany(cascade= {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "film")
    @JsonManagedReference
    private List<FilmProducerModel> filmProducers;
    private String winner;
}
