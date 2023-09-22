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
@ToString
@Entity
@Table(name="tab_producer")
public class ProducerModel {
    @Id
    @With
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "producer")
    @JsonManagedReference
    private List<FilmProducerModel> filmProducers;
}
