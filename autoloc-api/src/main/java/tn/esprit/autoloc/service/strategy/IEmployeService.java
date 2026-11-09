package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Employe;

import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    List<Employe> listerEmployes();
}