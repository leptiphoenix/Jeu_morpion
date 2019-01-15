/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

/**
 *
 * @author deniaul
 */
public class Case {
    private int numCase;
    private Joueur joueurAyantCoche;
    
    public Case(int numCase,Joueur joueurAyantCoche){
        this.numCase=numCase;
        this.joueurAyantCoche=joueurAyantCoche;   
    }

    /**
     * @return the numCase
     */
    public int getNumCase() {
        return numCase;
    }

    /**
     * @return the joueurAyantCoche
     */
    public Joueur getJoueurAyantCoche() {
        return joueurAyantCoche;
    }

    /**
     * @param joueurAyantCoche the joueurAyantCoche to set
     */
    public void setJoueurAyantCoche(Joueur joueurAyantCoche) {
        this.joueurAyantCoche = joueurAyantCoche;
    }
    
    
}
