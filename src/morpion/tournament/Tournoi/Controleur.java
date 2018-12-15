/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.Observable;
import java.util.Observer;
import morpion.tournament.Partie.Message.*;
import morpion.tournament.Partie.VueGrille;

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
    private Boolean erreur = false;
    private VueGrille vueGrille;

    public Controleur() {
        tournoi = new Tournoi();
        vueSelection = new VueSelection(tournoi.getListeParticipants());
        vueSelection.afficher();
        vueSelection.addObserver(this);
        vueGrille = new VueGrille("Lois","Guillaume");
        vueGrille.addObserver(this);
        vueGrille.afficher();
        vuePool = new VuePool(4);
        vuePool.afficher();

    }

    public void ajouter(String clé, String surnom, Preference pref) {
        vueModif = new VueModif(clé, surnom, pref.ordinal()+1);
        vueModif.afficher();
        vueModif.addObserver(this);
    }

    public void terminer(Observable o) {
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
                ajouter("", "", Preference.VIEUX);
            } else if (((Message) arg).getAction() == Action.COMMENCE) {
                ((VueSelection) o).close();
            } else if (arg instanceof MessageParticipant) {
                MessageParticipant messageParticipant = (MessageParticipant) arg;
                Participant p = new Participant(messageParticipant.getSurnom().trim(), messageParticipant.getPref());
                System.out.println(p.getPref());
                if ((messageParticipant.getAction() == Action.VALIDE)) {
                    if (messageParticipant.getClé().equals("")) {
                        if (tournoi.getListeParticipants().get(p.getSurnom()) == null) {
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
                        if (tournoi.getListeParticipants().get(p.getSurnom()) == null) {
                            tournoi.getListeParticipants().remove(messageParticipant.getClé());
                            tournoi.addParticipant(p);
                            terminer(o);
                        } else if (tournoi.getListeParticipants().get(p.getSurnom()).getSurnom().equals(messageParticipant.getClé())) {
                            tournoi.getListeParticipants().replace(messageParticipant.getClé(), p);
                            terminer(o);
                        } else {
                            ((VueModif) o).actualiser(true);
                        }
                    }
                }
            } else if (arg instanceof MessageCle) {
                MessageCle messageCle = (MessageCle) arg;
                if (messageCle.getAction() == Action.MODIFIE) {
                    ajouter(messageCle.getClé(), tournoi.getListeParticipants().get(messageCle.getClé()).getSurnom(), tournoi.getListeParticipants().get(messageCle.getClé()).getPref());
                } else if (((Message) arg).getAction() == Action.SUPPRIME) {
                    tournoi.getListeParticipants().remove(messageCle.getClé());
                    vueSelection.actualiser(tournoi.getListeParticipants());
                    vueSelection.afficher();
                }
            }
        }
    }
}
