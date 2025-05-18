package org.example.covoiturage.service;

import org.example.covoiturage.dto.response.DemandeReponse;
import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Passager;
import org.example.covoiturage.entities.Trajet;
import org.example.covoiturage.repository.DemandeTrajetRepository;
import org.example.covoiturage.repository.PassagerRepository;
import org.example.covoiturage.security.entities.Role;
import org.example.covoiturage.security.entities.User;
import org.example.covoiturage.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassagerService {
    @Autowired
    private PassagerRepository passagerRepository;
    @Autowired
    private TrajetService trajetService;
    @Autowired
    private DemandeTrajetRepository demandeTrajetRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Passager> findAll() {
        return passagerRepository.findAll();
    }

    public Passager findById(Long id) {
        return passagerRepository.findById(id).get();
    }

    public Passager save(Passager passager) {
        passager.setNombreTrajets(0);
        return passagerRepository.save(passager);
    }

    public void delete(Long id) {
        passagerRepository.deleteById(id);
    }

    public List<Passager> findName(String name) {
        return passagerRepository.findByLastname(name);
    }

    public Passager findByUsername(String username) {
        User user= userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return passagerRepository.findByUser(user);


    }

    public void demandeTrajets(String username, Long idTrajet, DemandeTrajet demandeTrajet) {
        Passager passager = this.findByUsername(username);

        Trajet trajet = trajetService.findTrajetById(idTrajet);
        demandeTrajet.setTrajet(trajet);
        demandeTrajet.setPassager(passager);
        trajet.getDemandes().add(demandeTrajet);
        demandeTrajetRepository.save(demandeTrajet);
    }

    public Passager registerPassager(String username ,Passager passager) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getRole().add(Role.PASSAGER);
        passager.setUser(user);
        return passagerRepository.save(passager);
    }

    public List<DemandeReponse> findMydemandes(String username) {
        Passager passager = this.findByUsername(username);

        return demandeTrajetRepository.findByPassager(passager).stream().map(dm -> DemandeReponse.builder()
                        .id(dm.getId())
                        .prixDePassager(dm.getPrixDePassager())
                        .acceptation(dm.isAcceptation())
                        .dateDemande(dm.getDateDemande())
                        .pointDepart(dm.getTrajet().getPointDepart())
                        .pointArrive(dm.getTrajet().getPointArrive())
                        //.dateDepart(dm.getTrajet().getDateDepart())  // si LocalDateTime sinon convertir
                        //.nbrPlacesDemander(dm.getTrajet().getNombrePlaces()) // ou nombre de places demandées si différent
                        .build())
                .collect(Collectors.toList());
    }
}
