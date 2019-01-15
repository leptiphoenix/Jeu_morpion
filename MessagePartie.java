/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie.Message;

import morpion.tournament.Partie.Joueur;

/**
 *
 * @author gvine
 */
public class MessagePartie extends Message {
    
    private int numCase;
    private Joueur joueur;
    
    public MessagePartie(Action action,int numCase,Joueur j){
        super(action);
        this.numCase=numCase;
        joueur=j;
    }

    /**
     * @return the numCase
     */
    public int getNumCase() {
        return numCase;
    }

    /**
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }
}
