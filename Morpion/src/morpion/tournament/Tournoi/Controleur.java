/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.Observable;
import java.util.Observer;
import morpion.tournament.Partie.ControleurPartie;
import morpion.tournament.Partie.Message.*;

/**
 *
 * @author deniaul
 */
public class Controleur implements Observer {

    private VueSelection vueSelection;
    private VuePool vuePool;
    private VueScore vueScore;
    private Tournoi tournoi;
    private VueModif vueModif;
    private ControleurPartie partie = null;
    

    public Controleur() {
        tournoi = new Tournoi();
        tournoi.addParticipant(new Participant("a",0));
        tournoi.addParticipant(new Participant("b",0));
        tournoi.addParticipant(new Participant("c",0));
        tournoi.addParticipant(new Participant("d",0));
        tournoi.addParticipant(new Participant("e",0));
        tournoi.addParticipant(new Participant("f",0));
        vueSelection = new VueSelection(tournoi.getListeParticipants());
        vueSelection.afficher();
        vueSelection.addObserver(this);
    }

    public void ajouter(int clé, String surnom, Preference pref) {
        vueModif = new VueModif(clé, surnom, pref.ordinal()+1);
        vueModif.afficher();
        vueModif.addObserver(this);
    }

    public void terminer(Observable o) {
        tournoi.triAlpha();
        vueSelection.actualiser(tournoi.getListeParticipants());
        vueSelection.afficher();
        ((VueModif) o).close();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            if (((Message) arg).getAction() == Action.ANNULE) {
                ((VueModif) o).close();
            } else if (((Message) arg).getAction() == Action.AJOUTE) {
                ajouter(-1, "", Preference.VIEUX);
            } else if (((Message) arg).getAction() == Action.COMMENCE) {
                if (tournoi.getListeParticipants().size()<2){
                    vueSelection.msgErreur();
                } else { 
                    ((VueSelection) o).close();
                    tournoi.phaseSuivante();
                    vuePool = new VuePool(tournoi.getPhaseDePool());
                    vuePool.afficher();
                    vuePool.addObserver(this);
                    tournoi.getVueScore().addObserver(this);
                }
            
            }
                
             else if (((Message) arg).getAction() == Action.AFFICHESCORE) {
                tournoi.afficherScore();  
                
            }   
             else if (arg instanceof MessageParticipant) {
                MessageParticipant messageParticipant = (MessageParticipant) arg;
                Participant p = new Participant(messageParticipant.getSurnom().trim(), messageParticipant.getPref());
                if ((messageParticipant.getAction() == Action.VALIDE)) {
                    if (messageParticipant.getClé()==-1) {
                        if (!(tournoi.ListePContient(p))) {
                            if (!(p.getSurnom().equals(""))){
                                tournoi.addParticipant(p);
                                terminer(o);
                            }
                            else{
                            ((VueModif) o).close();
                            }
                        } else {
                            ((VueModif) o).actualiser(true);
                        }
                    } else {
                        if (tournoi.ListePContient(p)&& !(p.getSurnom().equals(tournoi.getListeParticipants().get(messageParticipant.getClé()).getSurnom()))) {
                            ((VueModif) o).actualiser(true);
                        } else {
                            tournoi.getListeParticipants().remove(messageParticipant.getClé());
                            tournoi.addParticipant(p);
                            terminer(o);
                        }
                    }
                }
            }
             else if (arg instanceof MessageCle) {
                MessageCle messageCle = (MessageCle) arg;
                if (messageCle.getAction() == Action.MODIFIE) {
                    ajouter(messageCle.getClé(), tournoi.getListeParticipants().get(messageCle.getClé()).getSurnom(), tournoi.getListeParticipants().get(messageCle.getClé()).getPref());
                } else if (((Message) arg).getAction() == Action.SUPPRIME) {
                    tournoi.getListeParticipants().remove(messageCle.getClé());
                    vueSelection.actualiser(tournoi.getListeParticipants());
                    vueSelection.afficher();
                }
            }
             else if (arg instanceof MessageJeu) {
                MessageJeu messageJeu = (MessageJeu) arg;
                if (partie==null){
                partie = new ControleurPartie(messageJeu.getP1(),messageJeu.getP2());}
            }
        }
    }
}
