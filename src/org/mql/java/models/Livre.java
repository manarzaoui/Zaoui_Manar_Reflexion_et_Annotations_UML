package org.mql.java.models;

import org.mql.java.annotations.InfoLivre;

@InfoLivre(titre = "Livre Générique", auteur = "Auteur Inconnu")
public class Livre implements Empruntable {
    private boolean emprunte;

    public void emprunter() {
        if (!emprunte) {
            System.out.println("Livre emprunté.");
            emprunte = true;
        } else {
            System.out.println("Le livre est déjà emprunté.");
        }
    }

    public void retourner() {
        if (emprunte) {
            System.out.println("Livre retourné.");
            emprunte = false;
        } else {
            System.out.println("Le livre n'est pas emprunté.");
        }
    }

    public void afficherInfo() {
        InfoLivre info = this.getClass().getAnnotation(InfoLivre.class);
        System.out.println("Titre du livre : " + info.titre());
        System.out.println("Auteur du livre : " + info.auteur());
    }
}

