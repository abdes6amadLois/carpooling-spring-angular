package org.example.covoiturage.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name="demandesTrajets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DemandeTrajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trajet_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Trajet trajet;
    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passager;
    private Double prixDePassager;
    private boolean acceptation;
    private Date dateDemande;
    //private int nbrPlacesDemander;
}
