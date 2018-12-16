/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import morpion.tournament.Partie.Message.*;

/**
 *
 * @author deniaul
 */
public class Controleur implements Observer{

    
    private HashMap<Integer,Case> cases;
    private HashMap<Integer,Joueur> joueurs;
    
            
    
    
    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        if (arg instanceof Message) {
            if (message.getCommande()==Commande.COCHER_CASE) {
                
            }
        }
    }
    
}
