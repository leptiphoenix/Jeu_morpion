/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.util.ArrayList;
import morpion.tournament.Tournoi.Participant;

/**
 *
 * @author deniaul
 */
public class Joueur {
    
    private ArrayList<Case> casesCochees = new ArrayList();
    private Signe signe;
    private Participant identité; 
    
    
    Joueur(Participant p, Signe s){
        signe = s;
        identité = p;
    }

    /**
     * @return the signe
     */
    public Signe getSigne() {
        return signe;
    }

    /**
     * @return the identité
     */
    public Participant getIdentité() {
        return identité;
    }
}
