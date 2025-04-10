package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "trajets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Trajet {

    @Id
    private Long id;
    private String description;
    private String pointDepart;
    private String pointArrive;
    private LocalDateTime dateDepart;
    private Double prix;
    private int nombrePlaces;
    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private Conducteur conducteur;


}
