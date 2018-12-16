/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author deniaul
 */
public class Tournoi {


    private ArrayList<Participant> listeParticipants = new ArrayList<>();
    private PhaseDePool phaseDePool = null;

    public Tournoi() {
    }
    
    public void phaseSuivante(){
        //if (phaseDePool == null){mélangerListe();}       
        nouvellePhaseDePool();
    }

    public void addParticipant(Participant p) {
        getListeParticipants().add(p);
    }
    
        public boolean ListePContient(Participant p){
        Boolean b = false;
        for (Participant pa : getListeParticipants()){
            if (pa.getSurnom().equals(p.getSurnom())){b=true;} 
        }
        return b;
    }
    public void triAlpha(){
        Collections.sort(getListeParticipants(),Participant.nom);
    }
    public void triScore(){
        Collections.sort(getListeParticipants(),Participant.score);
    }
    public void mélangerListe(){
        Collections.shuffle(getListeParticipants());
    }
    public void nouvellePhaseDePool(){
        if (phaseDePool==null){
            phaseDePool = new PhaseDePool(this,1);
        }
        else{
        phaseDePool = new PhaseDePool(this,phaseDePool.getNbPhase()+1);}
    }
    /**
     * @return the listeParticipants
     */
    public ArrayList<Participant> getListeParticipants() {
        return listeParticipants;
    }

    /**
     * @param aListeParticipants the listeParticipants to set
     */
    public void setListeParticipants(ArrayList<Participant> aListeParticipants) {
        listeParticipants = aListeParticipants;
    }

    /**
     * @return the phaseDePool
     */
    public PhaseDePool getPhaseDePool() {
        return phaseDePool;
    }
}
