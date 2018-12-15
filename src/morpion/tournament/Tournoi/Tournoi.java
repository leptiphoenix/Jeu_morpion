/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.HashMap;

/**
 *
 * @author deniaul
 */
public class Tournoi {
    
    private static HashMap<String,Participant> listeParticipants;
    private HashMap<Integer,Pool> listePools;
    
    public Tournoi(){
        listeParticipants = new HashMap<>();
        
    }

    /**
     * @return the listeParticipants
     */
    public static HashMap<String,Participant> getListeParticipants() {
        return listeParticipants;
    }
    
    public void addParticipant(Participant p){
        getListeParticipants().put(p.getSurnom(),p);
    }
}
