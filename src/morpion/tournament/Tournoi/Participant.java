/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.HashMap;

import static morpion.tournament.Tournoi.Preference.*;

/**
 *
 * @author deniaul
 */
public class Participant {

    /**
     * @return the scoreTournoi
     */
    public Score getScoreTournoi() {
        return scoreTournoi;
    }

    /**
     * @param scoreTournoi the scoreTournoi to set
     */
    public void setScoreTournoi(Score scoreTournoi) {
        this.scoreTournoi = scoreTournoi;
    }

    /**
     * @return the scorePool
     */
    public Score getScorePool() {
        return scorePool;
    }

    /**
     * @param scorePool the scorePool to set
     */
    public void setScorePool(Score scorePool) {
        this.scorePool = scorePool;
    }

    /**
     * @return the historiquePool
     */
    public HashMap<Integer,Score> getHistoriquePool() {
        return historiquePool;
    }

    /**
     * @param historiquePool the historiquePool to set
     */
    public void setHistoriquePool(HashMap<Integer,Score> historiquePool) {
        this.historiquePool = historiquePool;
    }

    /**
     * @return the historiqueTournoi
     */
    public HashMap<Integer,Score> getHistoriqueTournoi() {
        return historiqueTournoi;
    }

    /**
     * @param historiqueTournoi the historiqueTournoi to set
     */
    public void setHistoriqueTournoi(HashMap<Integer,Score> historiqueTournoi) {
        this.historiqueTournoi = historiqueTournoi;
    }
    
    String surnom;
    Score scoreTournoi;
    Score scorePool;
    HashMap<Integer,Score> historiquePool;
    HashMap<Integer,Score> historiqueTournoi;
    Preference pref;
 
    public Participant(String surnom,int pref){
        setSurnom(surnom);
        scoreTournoi = new Score();
        scorePool = new Score();
        historiquePool = new HashMap<>();
        historiqueTournoi = new HashMap<>();
        if (pref==1) {
            setPref(JEUNE);
        } else if (pref==2) {
            setPref(ADULTE);
        } else {
            setPref(VIEUX);
        }
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
}
