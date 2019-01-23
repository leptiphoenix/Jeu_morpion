/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Observable;
import javax.swing.JButton;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.MessageCle;
import morpion.tournament.Tournoi.PanelPerso;

/**
 *
 * @author deniaul
 */
public class ICase extends Observable{
    private int position;
    private EtatCase etatCase;
    private JButton bouton;
    private ActionListener clic = null;
    
    ICase(int pos){
        position = pos;
        etatCase = EtatCase.NON_COCHEE;
        bouton=new JButton(etatCase.toString());
    }

    /**
     * @param etatCase the etatCase to set
     */
    public void setEtatCase(Signe signe) {
        if (signe==Signe.X){
            etatCase = EtatCase.X;}
        else if (signe==Signe.O){
            etatCase = EtatCase.O;
        }
        else{
            etatCase = EtatCase.NON_COCHEE;
        }
        bouton.setText(signe.toString());
            bouton.setEnabled(false);
    }

    /**
     * @return the bouton
     */
    public JButton getBouton() {
        return bouton;
    }
    
    public void addActionListener(Signe signe)
    {
        if (clic==null){
        clic =new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEtatCase(signe);
                setChanged();
                notifyObservers(new MessageCle(Action.COCHER_CASE,position));
                clearChanged();
            }
        };
        bouton.addActionListener(clic);}
        else{System.out.println("pas plus de 1 actionlistener");}
    }
    
    public void removeActionListener(){
        bouton.removeActionListener(clic);
        clic = null;
    }
}
