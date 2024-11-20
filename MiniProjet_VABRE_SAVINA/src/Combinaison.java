/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Liza Savina
 */
public class Combinaison {
    int taille = 4;
    Pion [] elements;
    
    public Combinaison (int taille , Pion [] elements){
        this.taille=taille;
        this.elements=elements;
        
    }
    
     @Override
    public String toString () {
    return "la taille est "+ taille + ", et son nombre de element bon est : " + elements;
    
    
}
}
