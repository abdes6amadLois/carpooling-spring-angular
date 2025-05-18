package org.example.covoiturage.controller;

import org.example.covoiturage.dto.response.DemandeReponse;
import org.example.covoiturage.entities.Conducteur;
import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Passager;
import org.example.covoiturage.service.PassagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passager")
public class PassagerController {
    @Autowired
    PassagerService passagerService;

    @PostMapping("/add")
    public Passager registerPassager(
            @RequestBody Passager passager) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return  passagerService.registerPassager(username, passager);

    }

    @PostMapping("/")
    public Passager save(@RequestBody Passager passager) {
        return passagerService.save(passager);
    }

    @GetMapping("/")
    public List<Passager> findAll() {
        return passagerService.findAll();
    }

    @GetMapping("/search")
    public List<Passager> findByName(@RequestParam String name) {
        return passagerService.findName(name);
    }

    @PostMapping("/demande")
    public void demande(@RequestParam("idTrajet") Long idTrajet,@RequestBody DemandeTrajet demandeTrajet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        passagerService.demandeTrajets(auth.getName(), idTrajet, demandeTrajet);
    }

    @GetMapping("my-demandes")
    public List<DemandeReponse> findMyDemandes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return passagerService.findMydemandes(auth.getName());
    }
}
