/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.util.ArrayList;


/**
 *
 * @author Lois
 */
public class PhaseDePool {
    private ArrayList<Pool> listePools;
    private Tournoi tournoi;
    private int nbPhase;
    
    PhaseDePool(Tournoi t,int nb){
        this.tournoi = t;
        nbPhase=nb;
        créerPools();
        for(Participant p : t.getListeParticipants()){
            p.nouvellePool();
        }
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
        tournoi.getListeParticipants().get(0).enregistrerPartie(Resultat.VICTOIRE);
    }
    
    public void créerPools() {
        getTournoi().triScore();
        listePools = new ArrayList<>();
        for (int i = 0; i < (getTournoi().getListeParticipants().size()/3); i++) {
            ArrayList<Participant> joueurs = new ArrayList<>();
            for (int j = i * 3; j < (i+1)*3; j++) {
                joueurs.add(getTournoi().getListeParticipants().get(j));
            }
            getListePools().add(new Pool(joueurs));
        }
        switch (getTournoi().getListeParticipants().size() % 3) {
            case 1:
                getListePools().remove(getListePools().size()-1);
                ArrayList<Participant> joueurs;
                for (int i = 2; i >-1; i-=2) {
                    joueurs = new ArrayList<>();
                    for (int j = getTournoi().getListeParticipants().size()-i-2; j < getTournoi().getListeParticipants().size()-i; j++) {
                        joueurs.add(getTournoi().getListeParticipants().get(j));
                    }
                    getListePools().add(new Pool(joueurs));
                }
                break;
            case 2:
                joueurs = new ArrayList<>();
                for (int i = getTournoi().getListeParticipants().size()-2; i < getTournoi().getListeParticipants().size(); i++) {
                    joueurs.add(getTournoi().getListeParticipants().get(i));
                }
                getListePools().add(new Pool(joueurs));
                break;
        }
    }

 public ArrayList<Participant> participantsDeLaPoolDe(Participant p){
        return poolDuJoueur(p).getJoueurs();
    }
    public Pool poolDuJoueur(Participant p){
        Pool pool = null;
        for (Pool pol : getListePools()){
            for (Participant j : pol.getJoueurs()){
                if (j==p){pool=pol;}
            }
        }
        return pool;
    }
    
    public int nbPartiesRestantes(Participant p){
       int i;
       if (poolDuJoueur(p).getJoueurs().size()==2){
                i = 9 - p.getHistoriquePool().size();
            }
            else{
                i = 6 - p.getHistoriquePool().size();
            }
       return i;
    }
    /**
     * @return the listePools
     */
    public ArrayList<Pool> getListePools() {
        return listePools;
    }

    /**
     * @return the nbPhase
     */
    public int getNbPhase() {
        return nbPhase;
    }

    /**
     * @return the tournoi
     */
    public Tournoi getTournoi() {
        return tournoi;
    }

}
