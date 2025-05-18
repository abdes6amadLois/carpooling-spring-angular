package org.example.covoiturage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
    private String role; // "CONDUCTEUR" ou "PASSAGER"

    private String firstname;
    private String lastname;
    private String email;
    private String telephone;

    // champs spécifiques à Conducteur
    private String numCarteNational;
    private String photoPermis;
    private String photoCarteNational;

    // champs spécifiques à Passager
    private int nombrePersonnes;
    private int nombreTrajets;
}

