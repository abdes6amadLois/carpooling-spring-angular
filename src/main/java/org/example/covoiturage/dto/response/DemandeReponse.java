package org.example.covoiturage.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class DemandeReponse {
    private Long id;
    private Double prixDePassager;
    private boolean acceptation;
    private Date dateDemande;
    private String pointDepart;
    private String pointArrive;
    private LocalDateTime dateDepart;
    private int nbrPlacesDemander;
}
