package org.example.covoiturage.controller;

import org.example.covoiturage.entities.DemandeTrajet;
import org.example.covoiturage.entities.Trajet;
import org.example.covoiturage.service.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("trajet")
public class TrajetController {
    @Autowired
    private TrajetService trajetService;

    @GetMapping("/search")
    public List<Trajet> searchTrajets(
            @RequestParam(required = false) String pointDepart,
            @RequestParam(required = false) String pointArrive,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepart
    ) {
        return trajetService.search(pointDepart, pointArrive, dateDepart);
    }

    @GetMapping("day")
    public List<Trajet> dayTrajets(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepart){
        return trajetService.findAllTrajetsOfThisDay(dateDepart);
    }

    @GetMapping("/")
    public Trajet getTrajet(@RequestParam("id")Long id) {
        return trajetService.findTrajetById(id);
    }

    @GetMapping("all-demandes")
    public List<DemandeTrajet> getAllDemandes(@RequestParam("id")Long id) {
        return trajetService.findAllDemandeTrajets(id);
    }
}
