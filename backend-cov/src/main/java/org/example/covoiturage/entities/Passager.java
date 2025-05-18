package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.covoiturage.security.entities.User;


@Entity
@Table(name = "passagers")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Passager extends Personne{

    @Column(name = "nombre_personnes", nullable = true)
    private int nombrePersonnes;

    @Column(name = "nombre_trajets",nullable = true)
    private int nombreTrajets;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}
