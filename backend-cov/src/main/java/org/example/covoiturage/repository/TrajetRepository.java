package org.example.covoiturage.repository;

import org.example.covoiturage.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findByConducteurId(Long conducteurId);

    List<Trajet> findByDateDepartBetween(LocalDateTime start, LocalDateTime end);

    // Repository interface
    List<Trajet> findByPointDepartAndPointArriveAndDateDepartBetween(String pointDepart, String pointArrive, LocalDateTime dateDepart, LocalDateTime dateEnd);


    List<Trajet> findByPointDepart(String pointDepart);

    List<Trajet> findByPointArrive(String pointArrive);

    List<Trajet> findByPointDepartAndPointArrive(String pointDepart, String pointArrive);
}
