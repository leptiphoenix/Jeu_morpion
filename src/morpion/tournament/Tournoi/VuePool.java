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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.Message;

/**
 *
 * @author deniaul
 */
public class VuePool extends Observable{
    private JFrame window;
    private JButton btnRegles;
    private JButton btnScoreGeneral;
    private JButton btnJouer;
    private PanelPerso contentPanel;
    private PanelPerso closePanel;
    private PanelPerso infosPanel;
    private PanelPerso borderPanel;
    private PanelPerso selectionPanel;
    private Border blackline=BorderFactory.createLineBorder(Color.black,1);

    @SuppressWarnings("Convert2Lambda")
    public VuePool(int nbPool) {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1000, 400);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        PanelPerso mainPanel = new PanelPerso(new BorderLayout());
        window.add(mainPanel);

        contentPanel = new PanelPerso(new GridLayout(4+nbPool,1));
        
        closePanel = new PanelPerso(new GridLayout(2,2));
        mainPanel.add(closePanel,BorderLayout.NORTH);
        closePanel.add(new JLabel("Phase de pool un sur neuf"));
        closePanel.add(new JLabel("Fermer cette fenêtre annule le tournoi"));
        casev(closePanel);
        casev(closePanel);
        contentPanel.add(closePanel);
        
        infosPanel = new PanelPerso(new GridLayout(1,4));
        infosPanel.add(new JLabel("Joueur"));
        infosPanel.add(new JLabel("Score de pool"));
        infosPanel.add(new JLabel("Victoires/Défaites/Egalités"));
        infosPanel.add(new JLabel("Parties restantes"));
        contentPanel.add(infosPanel);
        
        
        for (int i=0; i<nbPool;i++){
            
        borderPanel = new PanelPerso();
        borderPanel.setBorder(blackline);
        //A RAJOUTER DES PANELS DE POOLS DANS CHAQUE BORDERPANEL
        contentPanel.add(borderPanel);
        }
        
        
        selectionPanel = new PanelPerso(new GridLayout(1,2));
        casev(selectionPanel);
        selectionPanel.add(new JLabel("Sélectionner deux joueurs prêt à jouer"));
        contentPanel.add(selectionPanel);
        
        
        PanelPerso bottomPanel = new PanelPerso(new GridLayout(1, 7));
        
        casev(bottomPanel);
         
        btnRegles = new JButton("Règles du jeu");
        btnRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.COMMENCE));
                clearChanged();
            }
        });
        bottomPanel.add(btnRegles);

        casev(bottomPanel);

        btnScoreGeneral = new JButton("Score général");
        btnScoreGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.COMMENCE));
                clearChanged();
            }
        });
        bottomPanel.add(btnScoreGeneral);
       
        casev(bottomPanel);
        
        btnJouer = new JButton("Jouer");
        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.AJOUTE));
                clearChanged();
            }
        });
        bottomPanel.add(btnJouer);
        
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
