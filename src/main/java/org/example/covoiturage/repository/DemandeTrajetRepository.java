package org.example.covoiturage.repository;

import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Passager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeTrajetRepository extends JpaRepository<DemandeTrajet, Long> {
   // List<DemandeTrajet> findByDemandeTrajetId(Long id);
    List<DemandeTrajet> findByPassager(Passager passager);
}
