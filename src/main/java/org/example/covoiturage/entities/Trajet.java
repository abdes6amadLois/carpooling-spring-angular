package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trajets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany
    private List<DemandeTrajet> demandes;

}
