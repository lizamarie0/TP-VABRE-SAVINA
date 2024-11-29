/*
 * SAVINA Liza
 * VABRE Aliénor
 * 20/11/2024
 */
package miniprojet_vabre_savina;

import java.util.ArrayList;

/**
 *
 * @author alien
 */
public class MiniProjet_VABRE_SAVINA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pion[] pions1 = { new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y') };
        Pion[] pions2 = { new Pion('G'), new Pion('G'), new Pion('B'), new Pion('Y') };
        
        Combinaison combinaison1 = new Combinaison(pions1);
        Combinaison combinaison2 = new Combinaison(pions2);
        
        System.out.println("Combinaison 1 : " + combinaison1.toString());
        System.out.println("Combinaison 2 : " + combinaison2.toString());
        
        int[] resultat = combinaison1.comparer(combinaison2);
        System.out.println("Pions bien places : " + resultat[0]);
        System.out.println("Pions mal places : " + resultat[1]);
        
        ArrayList<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R');
        couleursDisponibles.add('B');
        couleursDisponibles.add('G');
        couleursDisponibles.add('Y');

        Combinaison combinaisonAleatoire = Combinaison.genereAleatoire(4, couleursDisponibles);
        System.out.println("Combinaison aleatoire : " + combinaisonAleatoire.toString());
    }
    
}
