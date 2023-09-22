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
@Table(name="tab_studio")
public class StudioModel {
    @Id
    @With
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade= {CascadeType.ALL, CascadeType.REMOVE}, mappedBy = "studio")
    @JsonManagedReference
    private List<FilmStudioModel> filmStudios;
}
