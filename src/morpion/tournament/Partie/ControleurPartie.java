/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import morpion.tournament.Partie.Message.*;
import morpion.tournament.Tournoi.Participant;

/**
 *
 * @author deniaul
 */
public class ControleurPartie implements Observer{

    
    private HashMap<Integer,Case> cases;
    private ArrayList<Joueur> joueurs = new ArrayList();
    private VueGrille vueGrille;
    
    public ControleurPartie(Participant p1,Participant p2){
        System.out.println("C'est parti");
        joueurs.add(new Joueur(p1,Signe.X));
        joueurs.add(new Joueur(p2,Signe.O));
        vueGrille = new VueGrille(joueurs.get(0).getIdentité().getSurnom(),joueurs.get(1).getIdentité().getSurnom());
        vueGrille.addObserver(this);
        vueGrille.afficher();
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        if (arg instanceof Message) {
            if (message.getCommande()==Commande.COCHER_CASE) {
                
            }
        }
    }
    
}
