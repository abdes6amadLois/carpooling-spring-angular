package org.example.covoiturage.repository;

import org.example.covoiturage.entities.Passager;
import org.example.covoiturage.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassagerRepository extends JpaRepository<Passager, Long> {
    Optional<Passager> findByEmail(String email);

    List<Passager> findByLastname(String name);

    Passager findByUser(User user);
}
