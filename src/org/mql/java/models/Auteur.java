package org.mql.java.models;

import org.mql.java.annotations.InfoAuteur;

@InfoAuteur(nom = "Jane ", anneeNaissance = 1775,prénom ="Austen")
public class Auteur {
    private String nom;
    private int anneeNaissance;

    public Auteur(String nom, int anneeNaissance) {
        this.nom = nom;
        this.anneeNaissance = anneeNaissance;
    }

    public void afficherInfo() {
        InfoAuteur infoAuteur = this.getClass().getAnnotation(InfoAuteur.class);
        System.out.println("Auteur : " + infoAuteur.nom());
        System.out.println("Année de naissance : " + infoAuteur.anneeNaissance());
    }
}
