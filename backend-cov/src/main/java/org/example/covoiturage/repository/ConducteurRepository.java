package org.example.covoiturage.repository;

import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
    Optional<Conducteur> findByEmail(String email);



    List<Conducteur> findByLastname(String lastName);



    Conducteur findByUser(User user);
}
