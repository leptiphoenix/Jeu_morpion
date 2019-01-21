/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.ArrayList;



/**
 *
 * @author deniaul
 */
public class Pool {
    private ArrayList<Participant> joueurs;
    
    Pool(ArrayList<Participant> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * @return the joueurs
     */
    public ArrayList<Participant> getJoueurs() {
        return joueurs;
    }
}
