/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet_vabre_savina;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Liza Savina
 */
public class PlateauDeJeu {
    Combinaison combinaisonSecrete;
    ArrayList<Combinaison> tentatives;
    ArrayList<String> reponses;
    int nbToursMax;
    
    
    public PlateauDeJeu(Combinaison combinaisonSecrete, int nbToursMax) {
        this.combinaisonSecrete = combinaisonSecrete;
        this.nbToursMax = nbToursMax;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
    
    
    }
    
    public void proposerCombinaison(Combinaison tentative) {
        if (tentatives.size() >= nbToursMax) {
            System.out.println("Nombre maximal de tours atteint !");
        }
        
        tentatives.add(tentative);

    
    
    String indices = calculerIndices(tentative);
        reponses.add(indices);

        // Afficher les résultats pour cette tentative
        System.out.println("Tentative : " + tentative);
        System.out.println("Indices : " + indices);
    }
    
    
    public boolean estVictoire() {
        if (tentatives.isEmpty()) return false;
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return combinaisonSecrete.equals(derniereTentative);
    }

    // Vérifier si le joueur a perdu
    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }
}

