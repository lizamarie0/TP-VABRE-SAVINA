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
    int nbToursMax;
    int tailleCombinaison;
    /* Pion[][] matPions = new Pion[8][4];  => ?????????*/
    
    
    public Partie(int tailleCombinaison, int nbToursMax, ArrayList<Character> couleursDisponibles){
        this.couleursDisponibles = couleursDisponibles;
        Combinaison combinaisonSecrete = Combinaison.genereAleatoire(tailleCombinaison, couleursDisponibles);
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
        this.nbToursMax = nbToursMax;
        this.tailleCombinaison = tailleCombinaison;
    }
    
     public void afficherRegles() {
        System.out.println("Bienvenu sur le jeu du Mastermind:");
        System.out.println("Vous devez deviner une combinaison secrète composée de couleurs.");
        System.out.println("Chaque tentative doit être composée de la même taille que la combinaison secrète.");
        System.out.println("Pour chaque tentative, le système vous donnera des indices :");
        System.out.println("- Un pion noir signifie que la couleur est bien placée.");
        System.out.println("- Un pion blanc signifie que la couleur est présente mais mal placée.");
        System.out.println("Vous avez un nombre limité de tours pour trouver la bonne combinaison.");
    }
    
     private Combinaison creerCombinaisonDepuisEntree(String entree) {
    Pion[] pions = new Pion[entree.length()];

    for (int i = 0; i < entree.length(); i++) {
        char couleur = entree.charAt(i);

        if (!couleursDisponibles.contains(couleur)) {
            throw new IllegalArgumentException("Couleur invalide : " + couleur + ". Couleurs valides : " + couleursDisponibles);
        }

        pions[i] = new Pion(couleur);
    }

    return new Combinaison(pions);
}
     
     
     
     
     public void LancerPartie(){
         Scanner sc = new Scanner(System.in);
         int tour = 1;
        
         
        /* while (tour <= plateau.nbToursMax &&){
             plateau.afficherPlateau();
            
             System.out.println("Tour : " + tour + ", proposer une combinaison : ");
             combinaison  = sc.nextLine();
             
             for (int i = 0; i < entree.length() ; i++){
                 
             } */
        
        while (!plateau.estVictoire() && !plateau.estDefaite()) {
        // Afficher le plateau
        plateau.afficherPlateau();
        
        // vérifier que le nombre de tentatives ne dépassent pas le nombre maximal.
        if (tour == nbToursMax){
            plateau.estDefaite();
            System.out.println("Dommage, vous avez perdu... La combinaison secrète était : " + plateau.combinaisonSecrete);
            break;
        }

        // Demander une tentative valide
        Combinaison tentative = null;
        while (tentative == null) {
            System.out.println("Tour " + tour + " : Proposez une combinaison (par exemple: 'RGBY'):");
            String entree = sc.nextLine().toUpperCase();

            if (entree.length() != plateau.combinaisonSecrete.taille) {
                System.out.println("Erreur : La combinaison doit comporter " + plateau.combinaisonSecrete.taille + " couleurs.");
                continue;
            }

            try {
                tentative = creerCombinaisonDepuisEntree(entree);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        // Proposer la tentative au plateau
        plateau.proposerCombinaison(tentative);

        // Vérifier si la partie est terminée
        if (plateau.estVictoire()) {
            System.out.println("Bravo ! Vous avez trouve la combinaison secrete !");
            break;
        } else if (plateau.estDefaite()) {
            System.out.println("Dommage, vous avez perdu... La combinaison secrete était : " + plateau.combinaisonSecrete);
            break;
        }

        tour++;
    }
}
             
}
     
