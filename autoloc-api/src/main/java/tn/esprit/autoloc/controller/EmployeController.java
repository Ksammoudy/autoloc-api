package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Employe; // adapte le package de ton entité
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employe")
public class EmployeController {

    IEmployeService employeService;

    // http://localhost:8089/autoloc/employe/retrieve-all-employes
    @GetMapping("/retrieve-all-employes")
    public List<Employe> getEmployes() {
        return employeService.listerEmployes();
    }

    // http://localhost:8089/autoloc/employe/add-employe
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe e) {
        return employeService.ajouterEmploye(e);
    }
}