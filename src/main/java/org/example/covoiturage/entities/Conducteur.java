package org.example.covoiturage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



@Entity(name = "conducteurs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Conducteur extends User {

    private String numCarteNational;
    private String PhotoPermis;
    private String PhotoCarteNational;


    @Override
    public String getRole() {
        return "CONDUCTEUR";
    }
}
