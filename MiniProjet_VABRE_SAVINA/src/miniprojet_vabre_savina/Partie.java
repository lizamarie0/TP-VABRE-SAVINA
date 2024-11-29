/*
 * SAVINA Liza
 * VABRE Aliénor
 * 29/11/2024
 */
package miniprojet_vabre_savina;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alien
 */
public class Partie {
    
    PlateauDeJeu plateau;
    ArrayList<Character> couleursDisponibles;
    
    public Partie(int tailleCombinaison, int nbToursMax, ArrayList<Character> couleursDisponibles){
        this.couleursDisponibles = couleursDisponibles;
        Combinaison combinaisonSecrete = Combinaison.genereAleatoire(tailleCombinaison, couleursDisponibles);
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }
    
     public void afficherRegles() {
        System.out.println("Règles du jeu :");
        System.out.println("Vous devez deviner une combinaison secrète composée de couleurs.");
        System.out.println("Chaque tentative doit être composée de la même taille que la combinaison secrète.");
        System.out.println("Pour chaque tentative, le système vous donnera des indices :");
        System.out.println("- Un pion noir signifie que la couleur est bien placée.");
        System.out.println("- Un pion blanc signifie que la couleur est présente mais mal placée.");
        System.out.println("Vous avez un nombre limité de tours pour trouver la bonne combinaison.");
    }
    
     public void LancerPartie(){
         Scanner sc = new Scanner(System.in);
         int tour = 1;
         String entree;
         
         while (tour <= plateau.nbToursMax){
             plateau.afficherPlateau();
            
             System.out.println("Tour : " + tour + ", proposer une combinaison : ");
             entree  = sc.nextLine();
             
             
         }
     }
}
