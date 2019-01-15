/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import morpion.tournament.Partie.Message.*;
import morpion.tournament.Tournoi.Participant;
import morpion.tournament.Partie.Message.MessagePartie;

/**
 *
 * @author deniaul
 */
public class ControleurPartie implements Observer{

    
    private HashMap<Integer,Case> cases;
    private Joueur[] joueurs = new Joueur[2];
    private VueGrille vueGrille;
    private Joueur joueurActuel;
    
    public ControleurPartie(Participant p1,Participant p2){
        System.out.println("C'est parti");
        joueurs[0]=(new Joueur(p1,Signe.X));
        joueurs[1]=(new Joueur(p2,Signe.O));
        vueGrille = new VueGrille(joueurs[0].getIdentité().getSurnom(),joueurs[1].getIdentité().getSurnom());
        vueGrille.addObserver(this);
        vueGrille.afficher();
    }
    
    public void joueurSuivant(){
        if(joueurActuel==joueurs[0]){
            setJoueurActuel(joueurs[1]);
        } else {
            setJoueurActuel(joueurs[0]);
        }
    }
    
    public void verifierFinPartie(){
        ArrayList<Integer> listeCasesJoueur= new ArrayList<>();
        //if (!touteqsCartesCochees){
            for (Integer numCase : cases.keySet()){
                if (cases.get(numCase).getJoueurAyantCoche()==joueurActuel){
                    listeCasesJoueur.add(numCase);
                }
            }
 
            for (int i=1;i<8;i=i+3){
                if (listeCasesJoueur.contains(i) && listeCasesJoueur.contains(i+1) && listeCasesJoueur.contains(i+2)){
                    finMatch();
                }   
            }
           
            for (int i=1;i<4;i=i++){
                if (listeCasesJoueur.contains(i) && listeCasesJoueur.contains(i+3) && listeCasesJoueur.contains(i+6)){
                    finMatch();
                }   
            }
            
            if (listeCasesJoueur.contains(1) && listeCasesJoueur.contains(5) && listeCasesJoueur.contains(9)){
                    finMatch();
            }   
            
        
             if (listeCasesJoueur.contains(3) && listeCasesJoueur.contains(5) && listeCasesJoueur.contains(7)){
                    finMatch();
             }   
            
            
//            
//        } else {
//            matchNul();
//        }
    }
    
    public void nextTurn(){
        joueurSuivant();
        verifierFinPartie();
        
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof MessagePartie) {
            MessagePartie messagePartie = (MessagePartie) arg;
            if (messagePartie.getAction()==Action.COCHER_CASE) {
                getCases().get(messagePartie.getNumCase()).setJoueurAyantCoche(messagePartie.getJoueur());
                //Cocher avec la vue
                nextTurn();
            }
        }
    }

    /**
     * @return the cases
     */
    public HashMap<Integer,Case> getCases() {
        return cases;
    }

    /**
     * @return the joueurs
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * @return the vueGrille
     */
    public VueGrille getVueGrille() {
        return vueGrille;
    }

    /**
     * @return the joueurActuel
     */
    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    /**
     * @param joueurActuel the joueurActuel to set
     */
    public void setJoueurActuel(Joueur joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    private void finMatch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void matchNul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
