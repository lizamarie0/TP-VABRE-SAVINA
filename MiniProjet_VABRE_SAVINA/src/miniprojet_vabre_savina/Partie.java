/*
 * SAVINA Liza
 * VABRE Aliénor
 * 29/11/2024
 */
package miniprojet_vabre_savina;

/**
 *
 * @author alien
 */
public class Partie {
    
    PlateauDeJeu plateau;
    List<Character> couleursDisponibles;
    
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles){
        this.couleursDisponibles = couleursDisponibles;
        Combinaison combinaisonSecrete = genereCombinaisonSecrete(tailleCombinaison);
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }
    
}
