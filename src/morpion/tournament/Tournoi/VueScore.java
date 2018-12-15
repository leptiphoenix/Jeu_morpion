/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.Message;

/**
 *
 * @author deniaul
 */
public class VueScore  extends Observable{
    private JFrame window;
    private JButton btnSuivant;
    private JButton details;
    private PanelPerso trierPanel;
    private PanelPerso infosPanel;
    private PanelPerso detailsPanel;
    private PanelPerso contentPanel;
    private PanelPerso borderPanel;
    private String[] listetri={"par score","par surnom"};
    private JComboBox tri = new JComboBox(listetri);
    private Border blackline=BorderFactory.createLineBorder(Color.black,1);

    @SuppressWarnings("Convert2Lambda")
    public VueScore() {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1000, 400);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        PanelPerso mainPanel = new PanelPerso(new BorderLayout());
        window.add(mainPanel);

        contentPanel = new PanelPerso(new GridLayout(4,1));
        
        trierPanel = new PanelPerso(new GridLayout(2,2));
        casev(trierPanel);
        trierPanel.add(new JLabel("Trier par :"),tri);
        casev(trierPanel);
        casev(trierPanel);
        contentPanel.add(trierPanel);
           
            
        infosPanel = new PanelPerso(new GridLayout(1,3));
        infosPanel.add(new JLabel("Surnom"));
        infosPanel.add(new JLabel("Score"));
        casev(infosPanel);
        contentPanel.add(infosPanel);
        
        
        borderPanel = new PanelPerso(new GridLayout(1,1));
        borderPanel.setBorder(blackline);
        for (Participant p : Tournoi.getListeParticipants().values()){
            detailsPanel = new PanelPerso(new GridLayout(1,3));
            detailsPanel.add(new JLabel(p.getSurnom()));
            detailsPanel.add(new JLabel(p.getScoreTournoi().toString()));
            detailsPanel.add(details);
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setChanged();
                    //notifyObservers(new Message());
                    clearChanged();
                }
            });
            borderPanel.add(detailsPanel);
            
        }
        
        contentPanel.add(borderPanel);
        
        
        PanelPerso bottomPanel = new PanelPerso(new GridLayout(1, 7));
        
        casev(bottomPanel);
         
        btnSuivant = new JButton("Règles du jeu");
        btnSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.COMMENCE));
                clearChanged();
            }
        });
        bottomPanel.add(btnSuivant);

        casev(bottomPanel);

   
        
        contentPanel.add(bottomPanel,BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        

    }

    public void afficher() {
        this.window.setVisible(true);
    }

    private void casev(PanelPerso panel) {
        panel.add(new JLabel(""));
    }

    void close() {
        this.window.dispose();
    }
}

