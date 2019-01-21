/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie.Message;

/**
 *
 * @author deniaul
 */
public class MessageParticipant extends MessageCle {
  
    private String surnom;
    private int pref;
    
    public MessageParticipant(Action action,int clé,String surnom,int pref){
        super(action,clé);
        setSurnom(surnom);
        setPref(pref);

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
     * @return the pref
     */
    public int getPref() {
        return pref;
    }

    /**
     * @param pref the pref to set
     */
    public void setPref(int pref) {
        this.pref = pref;
    }

}
