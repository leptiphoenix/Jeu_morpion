/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

/**
 *
 * @author deniaul
 */
public class Score {
    private int victoire = 0;
    private int defaite = 0;
    private int egalite = 0;
    Score(){
    }
    public int getPoints(){
        return getVictoire() - getDefaite();
    }
    @Override
    public String toString() {
        return victoire + "/" + defaite + "/" + egalite ;
    }
    /**
     * @return the victoire
     */
    public int getVictoire() {
        return victoire;
    }

    /**
     * @param victoire the victoire to set
     */
    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }

    /**
     * @return the defaite
     */
    public int getDefaite() {
        return defaite;
    }

    /**
     * @param defaite the defaite to set
     */
    public void setDefaite(int defaite) {
        this.defaite = defaite;
    }

    /**
     * @return the egalite
     */
    public int getEgalite() {
        return egalite;
    }

    /**
     * @param egalite the egalite to set
     */
    public void setEgalite(int egalite) {
        this.egalite = egalite;
    }
}
