/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import morpion.tournament.Partie.Message.*;

/**
 *
 * @author deniaul
 */
public class VuePerso extends Observable{
    
    private final JFrame window ;
    private final JButton btnSuivant;
    private JLabel msgResultats;
    private JLabel msgPoints;
    private PanelPerso mainPanel;
    private PanelPerso contentPanel;
    
    
    @SuppressWarnings("Convert2Lambda")
    public VuePerso(String cléGagnant, String cléPerdant) {
        
        window = new JFrame();
        // Définit la taille de la fenêtre en pixels
        window.setSize(400, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        mainPanel = new PanelPerso(new BorderLayout());
        mainPanel.setOpaque(true);
        window.add(mainPanel);
        
        contentPanel = new PanelPerso(new GridLayout(1,3));
    
        
        casev(contentPanel);
        contentPanel.add(msgResultats);
        contentPanel.add(msgPoints);
                
        mainPanel.add(contentPanel);
        
        //création du bouton valider
        btnSuivant = new JButton("Valider");
        btnSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new MessageResultat(Action.VALIDE));
                clearChanged();
            }
        });
        
      
        btnSuivant.setBorderPainted(false);
      
    }
    
    
    
    
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
 
    //crée une case vide
    private void casev(PanelPerso panel){
        panel.add(new JLabel(""));
    }
    
    
    
    void close() {
        this.window.dispose();
    }
}


