package org.example.covoiturage.service;

import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Trajet;
import org.example.covoiturage.entities.Vehicle;
import org.example.covoiturage.repository.ConducteurRepository;
import org.example.covoiturage.repository.DemandeTrajetRepository;
import org.example.covoiturage.repository.VehicleRepository;
import org.example.covoiturage.security.entities.Role;
import org.example.covoiturage.security.entities.User;
import org.example.covoiturage.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConducteurService {
    @Autowired
    ConducteurRepository conducteurRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    DemandeTrajetRepository demandeTrajetRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TrajetService trajetService;

    public List<Conducteur> findAll() {
        return conducteurRepository.findAll();
    }

    public Conducteur findById(Long id) {
        return conducteurRepository.findById(id).get();
    }

    public Conducteur AddVehicle(Long id,Vehicle vehicle) {
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur not found"));


        vehicle.setOwner(conducteur);


        conducteur.getVehicles().add(vehicle);


        vehicleRepository.save(vehicle);
        return conducteurRepository.save(conducteur);
    }

    public Conducteur findByUserName(String userName) {
        User user = userRepository.findByUsername(userName).get();
        return conducteurRepository.findByUser(user);
    }

    public Conducteur save(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public Conducteur update(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public void delete(Long id) {
        conducteurRepository.deleteById(id);
    }

    public List<Conducteur> findByName(String name) {
        return conducteurRepository.findByLastname(name);
    }

    public void accepterUnDemande(Long id) {
        DemandeTrajet demandeTrajet=demandeTrajetRepository.findById(id).get();
        demandeTrajet.setAcceptation(true);
        demandeTrajetRepository.save(demandeTrajet);
    }


    public Conducteur registerConducteur(String username, Conducteur cond) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Conducteur conducteur = Conducteur.builder()
                .firstname(cond.getFirstname())
                .lastname(cond.getLastname())
                .email(cond.getEmail())
                .telephone(cond.getTelephone())
                .numCarteNational(cond.getNumCarteNational())
                .PhotoPermis(cond.getPhotoPermis())
                .PhotoCarteNational(cond.getPhotoCarteNational())
                .user(user)
                .build();
        conducteur.getUser().getRole().add(Role.CONDUCTEUR);

        conducteurRepository.save(conducteur);
        return conducteur;
    }

    public List<Trajet> findMyTrajets(String username) {
        this.findByUserName(username);
        return trajetService.findAllTrajetOfConducteur(this.findByUserName(username).getId());
    }

    public void accepterDemande(Long id, String username) {
        Conducteur conducteur = this.findByUserName(username);
        DemandeTrajet dm = demandeTrajetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande non trouvée"));

        if (!dm.getTrajet().getConducteur().equals(conducteur)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'êtes pas autorisé à accepter cette demande");
        }

        dm.setAcceptation(true);
        demandeTrajetRepository.save(dm);
    }

}
