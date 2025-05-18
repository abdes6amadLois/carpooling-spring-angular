package org.example.covoiturage.service;

import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Trajet;
import org.example.covoiturage.repository.ConducteurRepository;
import org.example.covoiturage.repository.TrajetRepository;
import org.example.covoiturage.security.entities.User;
import org.example.covoiturage.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrajetService {

    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private ConducteurService conducteurService;
    @Autowired
    private UserRepository userRepository;

    public Trajet saveTrajet(Trajet trajet,String username) {

        Conducteur conducteur= conducteurService.findByUserName(username);
        trajet.setConducteur(conducteur);
        return trajetRepository.save(trajet);
    }

    public List<Trajet> findAllTrajets() {
        return trajetRepository.findAll();
    }

    public Trajet findTrajetById(Long id) {
        return trajetRepository.findById(id).orElse(null);
    }

    public void deleteTrajetById(Long id) {
        trajetRepository.deleteById(id);
    }

    public List<Trajet> findAllTrajetOfConducteur(Long id) {
        return trajetRepository.findByConducteurId(id);
    }

    public List<Trajet> findAllTrajetsOfThisDay(LocalDate localDate) {
        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = localDate.atTime(23, 59, 59);
        return trajetRepository.findByDateDepartBetween(start, end);
    }

    public List<Trajet> search(String pointDepart, String pointArrive, LocalDate localDate) {
        // Case when only the localDate is provided
        if ((pointDepart == null || pointDepart.trim().isEmpty())
                && (pointArrive == null || pointArrive.trim().isEmpty())
                && localDate != null) {
            LocalDateTime start = localDate.atStartOfDay(); // start of the day
            LocalDateTime end = localDate.atTime(23, 59, 59); // end of the day
            return trajetRepository.findByDateDepartBetween(start, end);
        }

        // Case when only pointDepart is provided
        else if ((pointArrive == null || pointArrive.trim().isEmpty())
                && (pointDepart != null && !pointDepart.trim().isEmpty())
                && localDate == null) {
            return trajetRepository.findByPointDepart(pointDepart);
        }

        // Case when only pointArrive is provided
        else if ((pointDepart == null || pointDepart.trim().isEmpty())
                && (pointArrive != null && !pointArrive.trim().isEmpty())
                && localDate == null) {
            return trajetRepository.findByPointArrive(pointArrive);
        }

        // Case when both pointDepart and pointArrive are provided, and localDate is provided
        else if ((pointDepart != null && !pointDepart.trim().isEmpty())
                && (pointArrive != null && !pointArrive.trim().isEmpty())
                && localDate != null) {
            LocalDateTime start = localDate.atStartOfDay();
            LocalDateTime end = localDate.atTime(23, 59, 59);
            return trajetRepository.findByPointDepartAndPointArriveAndDateDepartBetween(pointDepart, pointArrive, start, end);
        }

        // Case when both pointDepart and pointArrive are provided without localDate
        else if ((pointDepart != null && !pointDepart.trim().isEmpty())
                && (pointArrive != null && !pointArrive.trim().isEmpty())
                && localDate == null) {
            return trajetRepository.findByPointDepartAndPointArrive(pointDepart, pointArrive);
        }

        // Case when only date range is provided (without specific points)
        else if (localDate != null &&
                (pointDepart == null || pointDepart.trim().isEmpty())
                && (pointArrive == null || pointArrive.trim().isEmpty())) {
            LocalDateTime start = localDate.atStartOfDay();
            LocalDateTime end = localDate.atTime(23, 59, 59);
            return trajetRepository.findByDateDepartBetween(start, end);
        }

        // Default case when no valid parameters are provided, return empty list
        return new ArrayList<>();
    }

    public List<DemandeTrajet> findAllDemandeTrajets(Long idTrajet) {
        return findTrajetById(idTrajet).getDemandes();
    }

}
