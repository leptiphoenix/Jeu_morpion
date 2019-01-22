/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Partie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.MessageCle;
import morpion.tournament.Tournoi.PanelPerso;
import morpion.tournament.Tournoi.Participant;

/**
 *
 * @author deniaul
 */
public class VueGrille extends Observable {

    private HashMap<Integer, ICase> listeCases;
    private final JFrame window;
    private PanelPerso mainPanel;
    private PanelPerso closePanel;
    private PanelPerso contentPanel;
    private PanelPerso infosPanel;
    private PanelPerso grillePanel;
    private PanelPerso textePanel;
    private PanelPerso partiePanel;
    private Participant joueurCourant;
    private Border blackline = BorderFactory.createLineBorder(Color.black, 1);
    private ArrayList<ICase> liste = new ArrayList<>();

    @SuppressWarnings("Convert2Lambda")
    public VueGrille(String joueur1, String joueur2) {

     
        
        grillePanel = new PanelPerso(new GridLayout(3, 3));
        for (int i = 1; i < 10; i++) {
            ICase casei = new ICase();
            liste.add(casei);
            grillePanel.add(casei);
        }

        grillePanel.setBorder(blackline);

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(750, 300);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        mainPanel = new PanelPerso(new BorderLayout());
        window.add(mainPanel);

        closePanel = new PanelPerso(new GridLayout(1, 1));
        mainPanel.add(closePanel, BorderLayout.NORTH);
        closePanel.add(new JLabel("Fermer cette fenêtre annule la partie"));

        infosPanel = new PanelPerso(new GridLayout(2, 1));

        textePanel = new PanelPerso(new GridLayout(1, 2));
        textePanel.add(new JLabel("Surnom"));
        textePanel.add(new JLabel("Signe"));
        infosPanel.add(textePanel);

        partiePanel = new PanelPerso(new GridLayout(2, 2));
        partiePanel.add(new JLabel(joueur1));
        partiePanel.add(new JLabel("X"));
        partiePanel.add(new JLabel(joueur2));
        partiePanel.add(new JLabel("O"));
        infosPanel.add(partiePanel);

        contentPanel = new PanelPerso(new GridLayout(3, 2));

        // UN ACTUALISER EST A FAIRE POUR CHANGER LE TOUR DU JOUEUR(SIGNE,NOM)
        casev(contentPanel);
        contentPanel.add(closePanel);
        contentPanel.add(infosPanel);
        casev(contentPanel);
        contentPanel.add(new JLabel("Tour du joueur : " + getJoueurCourant()));
        contentPanel.add(grillePanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        joueurCourant.setSurnom(joueur1);

        for (ICase ic : liste) {
            if(joueurCourant.getSurnom().equals(joueur1)){
            ic.getBouton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                cocherCase(liste.indexOf(ic),Signe.X);
                notifyObservers(new MessageCle(Action.COCHER_CASE,liste.indexOf(ic)));
                clearChanged();
            }

            public void mouseEntered(MouseEvent e) {
                setChanged();
                cocherCase(liste.indexOf(ic), Signe.X);
                clearChanged();
            }

            public void mouseExited(MouseEvent e) {
                setChanged();
                decocherCase(liste.indexOf(ic), Signe.X);
                clearChanged();
            }
        });
        } else {
            ic.getBouton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                cocherCase(liste.indexOf(ic),Signe.O);
                notifyObservers(new MessageCle(Action.COCHER_CASE,liste.indexOf(ic)));
                clearChanged();
            }

            public void mouseEntered(MouseEvent e) {
                setChanged();
                cocherCase(liste.indexOf(ic), Signe.O);
                clearChanged();
            }

            public void mouseExited(MouseEvent e) {
                setChanged();
                decocherCase(liste.indexOf(ic), Signe.O);
                clearChanged(); 
            }
            });
        }
        }
    }
    
    public void actualiserPartie(){
        
        
    }
        
    public void cocherCase(int numCase, Signe signe) {
        liste.get(numCase).setEtatCase(signe);
    }
    public void decocherCase(int numCase, Signe signe) {
        liste.get(numCase).setEtatCase(null);
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

    /**
     * @return the joueurCourant
     */
    public Participant getJoueurCourant() {
        return joueurCourant;
    }
}
