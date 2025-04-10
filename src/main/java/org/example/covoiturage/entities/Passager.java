package org.example.covoiturage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name = "passagers")
public class Passager extends User{

    private int nombrePersonnes;
    private int nombreTrajets;

    @Override
    public String getRole() {
        return "PASSAGER";
    }
}
