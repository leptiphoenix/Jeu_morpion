/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.ArrayList;
import java.util.Comparator;
import static morpion.tournament.Tournoi.Preference.*;

/**
 *
 * @author deniaul
 */
public class Participant {
    
    private String surnom;
    private Score scoreTournoi;
    private Score scorePool;
    private ArrayList<Resultat> historiquePool = null;
    private ArrayList<ArrayList<Resultat>> historiqueTournoi = new ArrayList<ArrayList<Resultat>>();
    private Preference pref;
 
    public Participant(String surnom,int pref){
        setSurnom(surnom);
        scoreTournoi = new Score();
        scorePool = new Score();
        if (pref==1) {
            setPref(JEUNE);
        } else if (pref==2) {
            setPref(ADULTE);
        } else {
            setPref(VIEUX);
        }
    }
    public static Comparator<Participant> score = new Comparator<Participant>() {

        @Override
        public int compare(Participant p1, Participant p2) {
            return p2.getScoreTournoi().getPoints() - p1.getScoreTournoi().getPoints();
        }
    };
    public void nouvellePool(){
        if (!(historiquePool==null)){
        historiqueTournoi.add(getHistoriquePool());}
        historiquePool = new ArrayList<Resultat>() ;
    }
    
    public static Comparator<Participant> nom = new Comparator<Participant>() {

        @Override
        public int compare(Participant p1, Participant p2) {
            return p1.getSurnom().compareTo(p2.getSurnom());
        }
    };
    
    public void enregistrerPartie(Resultat resultat){
        getHistoriquePool().add(resultat);
        switch (resultat){
            case VICTOIRE:
                scorePool.setVictoire(scorePool.getVictoire()+1);
                break;
            case EGALITE:
                scorePool.setEgalite(scorePool.getEgalite()+1);
                break;
            case DEFAITE:
                scorePool.setDefaite(scorePool.getDefaite()+1);
                break;
        }
    }
    
    @Override
//    public int compare(Participant p) {
//        return p.getScoreTournoi().getPoints() - getScoreTournoi().getPoints();
//
//    }
    
    public String toString() {
        return "[ Surnom=" + surnom + ", scoreTouroi=" + getScoreTournoi() + ", scorePool=" + getScorePool() + "]";
    }
    
    /**
     * @return the surnom
     */
    public String getSurnom() {
        return surnom;
    }

    /**
     * @param surnom the surnom to set
     */
    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    /**
     * @param pref the pref to set
     */
    public void setPref(Preference pref) {
        this.pref = pref;
    }

    /**
     * @return the pref
     */
    public Preference getPref() {
        return pref;
    }

    /**
     * @return the scoreTournoi
     */
    public Score getScoreTournoi() {
        return scoreTournoi;
    }

    /**
     * @return the scorePool
     */
    public Score getScorePool() {
        return scorePool;
    }

    /**
     * @return the historiquePool
     */
    public ArrayList<Resultat> getHistoriquePool() {
        return historiquePool;
    }


}
