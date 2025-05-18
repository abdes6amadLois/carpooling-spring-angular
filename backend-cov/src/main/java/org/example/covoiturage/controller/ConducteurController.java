package org.example.covoiturage.controller;


import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.entities.Trajet;
import org.example.covoiturage.entities.Vehicle;
import org.example.covoiturage.service.ConducteurService;
import org.example.covoiturage.service.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("conducteur")
public class ConducteurController {

    @Autowired
    private ConducteurService conducteurService;
    @Autowired
    private TrajetService trajetService;

    @PostMapping("/add")
    public Conducteur registerConducteur(
            @RequestBody Conducteur cond) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Conducteur conducteur = conducteurService.registerConducteur(username, cond);
        return conducteur;
    }

    @PostMapping("/")
    public Conducteur conducteur(@RequestBody Conducteur conducteur) {
        return conducteurService.save(conducteur);
    }

    @GetMapping("/")
    public List<Conducteur> conducteurs() {
        return conducteurService.findAll();
    }

    @GetMapping("/SearchByName")
    public List<Conducteur> conducteurByName(@RequestParam("name") String name) {
        return conducteurService.findByName(name);
    }

    @PostMapping("/addVehicle")
    public Conducteur addVehicle(@RequestParam("id") Long id,@RequestBody Vehicle vehicle) {
        return conducteurService.AddVehicle(id, vehicle);
    }


    @PostMapping("/trajet")
    public Trajet createTrajet(@RequestBody Trajet trajet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return trajetService.saveTrajet(trajet, auth.getName());
    }

    @GetMapping("/allTrajet")
    public List<Trajet> allTrajet(@RequestParam("id") Long id) {
        return trajetService.findAllTrajetOfConducteur(id);
    }

    @PostMapping("accepter")
    public void accepter(@RequestParam("id") Long id) {
        conducteurService.accepterUnDemande(id);
    }

    @GetMapping("user")
    public Conducteur findByusername(@RequestParam("username") String username) {
        return conducteurService.findByUserName(username);
    }

    @GetMapping("my-trajets")
    public List<Trajet> MyTrajets() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return conducteurService.findMyTrajets(auth.getName());
    }

    @PostMapping("accepter-demande")
    public void accepterDemande(@RequestParam("id") Long demandeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        conducteurService.accepterDemande(demandeId,auth.getName());
    }

}
