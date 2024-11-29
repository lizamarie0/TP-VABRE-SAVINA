/*
 * SAVINA Liza
 * VABRE Aliénor
 * 20/11/2024
 */
package miniprojet_vabre_savina;

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
        Pion[] pions2 = { new Pion('R'), new Pion('G'), new Pion('B'), new Pion('Y') };

        Combinaison combinaison1 = new Combinaison(pions1);
        Combinaison combinaison2 = new Combinaison(pions2);
        
        
    }
    
}
