package org.example.covoiturage.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public abstract class User {
    @Id
    private Long id;

    private String name;
    private String email;
    private String password;
    private String telephone;

    public abstract String getRole();


}
