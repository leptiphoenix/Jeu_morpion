/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import morpion.tournament.Tournoi.PanelPerso;

/**
 *
 * @author deniaul
 */
public class ICase extends PanelPerso{
    private EtatCase etatCase;
    private JButton bouton;
    private ActionListener clic;
    
    ICase(){
        etatCase = EtatCase.NON_COCHEE;
        bouton=new JButton(etatCase.toString());
        this.add(bouton);
    }

    /**
     * @param etatCase the etatCase to set
     */
    public void setEtatCase(Signe signe) {
        if (signe==Signe.X){
            etatCase = EtatCase.X;}
        else{
            etatCase = EtatCase.O;
        }
        bouton.setText(etatCase.toString());
        bouton.setEnabled(false);
    }

    /**
     * @return the bouton
     */
    public JButton getBouton() {
        return bouton;
    }
    
}
