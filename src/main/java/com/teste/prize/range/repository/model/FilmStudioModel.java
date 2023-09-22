package com.teste.prize.range.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name="tab_film_studio")
public class FilmStudioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @With
    @JsonBackReference(value = "film")
    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmModel film;
    @With
    @JsonBackReference(value = "studio")
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioModel studio;
}
