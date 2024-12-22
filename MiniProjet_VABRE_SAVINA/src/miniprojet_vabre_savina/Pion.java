package miniprojet_vabre_savina;

import java.awt.Color;

/*
 * SAVINA Liza
 * VABRE Aliénor
 * 20/11/2024
 */

/**
 *
 * @author alien
 * @author Liza Savina
 */
public class Pion {
   private final Color couleur;
   
   public Pion(Color couleur){
   this.couleur = couleur;
   }
   
   public Color getCouleur(){
       return couleur;
   
    }
   
    /**
     *
     * @return
     */
    @Override
   public String toString(){
       return couleur.toString();
   }
   
}
