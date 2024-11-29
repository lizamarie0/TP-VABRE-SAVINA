package miniprojet_vabre_savina;


import java.util.ArrayList;
import java.util.Random;
import miniprojet_vabre_savina.Pion;

/*
 * SAVINA Liza
 * VABRE Aliénor
 * 20/11/2024
 */

/**
 *
 * @author Liza Savina
 */
public class Combinaison {
    
    Pion [] elements;
    int taille = elements.length;
    
    public Combinaison ( int taille, Pion [] elements){
        this.taille=taille;
        this.elements=elements;
        
    }
        
    public void genereAleatoire(int taille, ArrayList<Character>couleursDisponibles){
        Random random = new Random();
        Pion [] elements = new Pion[taille];
        
        for(int i = 0; i<taille; i++){
            
        }
    }
    
     @Override
    public String toString () {
    return "la taille est "+ taille + ", et son nombre de element bon est : " + elements;
    
    
}
}
