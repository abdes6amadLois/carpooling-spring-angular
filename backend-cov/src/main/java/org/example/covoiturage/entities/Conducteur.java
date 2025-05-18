package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.covoiturage.security.entities.User;

import java.util.List;


@Entity
@Table(name = "conducteurs")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Conducteur extends Personne {

    private String numCarteNational;
    private String PhotoPermis;
    private String PhotoCarteNational;
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
