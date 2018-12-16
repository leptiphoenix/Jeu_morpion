/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie.Message;

import morpion.tournament.Tournoi.Participant;

/**
 *
 * @author Lois
 */
public class MessageJeu extends Message{
    private Participant p1;
    private Participant p2;
    public MessageJeu(Action a,Participant p1,Participant p2){
        super(a);
        this.p1=p1;
        this.p2=p2;
    }

    /**
     * @return the p1
     */
    public Participant getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public Participant getP2() {
        return p2;
    }
}
