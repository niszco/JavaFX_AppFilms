package com.example.films;

import javafx.collections.ObservableList;

public class Films {
    private Integer rang;
    private String id;
    private String nom;
    private String acteur;
    private Integer annee;

    public int getRang() {
        return rang;
    }

    public Integer setRang(Integer rang) {
        this.rang = rang;
        return rang;
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String setNom(String nom) {
        this.nom = nom;
        return nom;
    }

    public String getActeur() {
        return acteur;
    }

    public String setActeur(String acteur) {
        this.acteur = acteur;
        return acteur;
    }

    public int getAnnee() {
        return annee;
    }

    public Integer setAnnee(Integer annee) {
        this.annee = annee;
        return annee;
    }

    public void add(ObservableList<Films> film) {
    }
}
