package org.example.covoiturage.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Vehicle {
    private String modelName;
    private int moduleYear;
    private Conducteur owner;
    private String matricule;
    private String PhotoVehicle;
}
