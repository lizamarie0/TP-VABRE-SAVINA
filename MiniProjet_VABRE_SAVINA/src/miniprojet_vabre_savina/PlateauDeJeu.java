/*
 * SAVINA Liza
 * VABRE Aliénor
 * 29/11/2024
 */
package miniprojet_vabre_savina;

import java.util.ArrayList;


/**
 *
 * @author Liza Savina
 * @author alien
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
            return;
        }
        
        tentatives.add(tentative);
    
    String indices = calculerIndices(tentative);
        reponses.add(indices);

        // Afficher les résultats pour cette tentative
        System.out.println("Tentative : " + tentative.toString());
        System.out.println("Indices : " + indices);
    }
    
     // Calculer les indices (pions noirs et blancs)
    private String calculerIndices(Combinaison tentative) {
        int[] resultats = combinaisonSecrete.comparer(tentative);
        return resultats[0] + " noirs, " + resultats[1] + " blancs";
    }
    
    public void afficherPlateau(){
        System.out.println("Historique des tentatives :");
        for (int i = 0; i < tentatives.size(); i++) {
            Combinaison tentative = tentatives.get(i);
            String reponse = reponses.get(i);
            System.out.println("Tentative " + (i + 1) + ": " + tentative + " | Reponse : " + reponse);
    }
}
    
    public boolean estVictoire() {
        if (tentatives.isEmpty()) return false;
        Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
        return true;
    }

    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }
}

