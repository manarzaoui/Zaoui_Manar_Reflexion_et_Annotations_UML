package org.mql.java.models;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> listeLivres;

    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
        System.out.println("Livre ajouté à la bibliothèque.");
    }

    public void afficherInformationsLivres() {
        System.out.println("Informations sur les livres de la bibliothèque :");
        for (Livre livre : listeLivres) {
            livre.afficherInfo();
            System.out.println(); 
        }
    }

    public void emprunterLivre(int index) {
        if (index >= 0 && index < listeLivres.size()) {
            Livre livre = listeLivres.get(index);
            livre.emprunter();
        } else {
            System.out.println("Index invalide.");
        }
    }

    public void retournerLivre(int index) {
        if (index >= 0 && index < listeLivres.size()) {
            Livre livre = listeLivres.get(index);
            livre.retourner();
        } else {
            System.out.println("Index invalide.");
        }
    }

    
}
