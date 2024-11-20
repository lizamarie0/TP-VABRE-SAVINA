/*
 * SAVINA Liza
 * VABRE Aliénor
 * 20/11/2024
 */

/**
 *
 * @author alien
 */
public class Pion {
   private Character couleur;
   
   public Pion(Character couleur){
   this.couleur = couleur;
   }
   
   public Character getCouleur(){
       return couleur;
   
    }
   
   public String toString(){
       return couleur.toString();
   }
   
}
