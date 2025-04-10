package org.example.covoiturage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name="demandesTrajets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DemandeTrajet {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;
    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passager;
    private Double prixDePassager;
    private boolean acceptation;
}
