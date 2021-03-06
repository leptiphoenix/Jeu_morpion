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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import morpion.tournament.Tournoi.PanelPerso;

/**
 *
 * @author deniaul
 */
public class VueGrille {

    /**
     * @return the liste
     */
    public ArrayList<ICase> getListe() {
        return liste;
    }

    private HashMap<Integer, ICase> listeCases;
    private final JFrame window;
    private PanelPerso mainPanel;
    private PanelPerso closePanel;
    private PanelPerso contentPanel;
    private PanelPerso infosPanel;
    private PanelPerso grillePanel;
    private PanelPerso textePanel;
    private PanelPerso partiePanel;
    private HashMap<Signe,String> joueurs = new HashMap();
    private Signe signeCourant = Signe.X;
    private Border blackline = BorderFactory.createLineBorder(Color.black, 1);
    ArrayList<ICase> liste = new ArrayList<>();

    @SuppressWarnings("Convert2Lambda")
    public VueGrille(String joueur1, String joueur2) {
        joueurs.put(Signe.X,joueur1);
        joueurs.put(Signe.O,joueur2);
        grillePanel = new PanelPerso(new GridLayout(3, 3));
        for (int i = 1; i < 10; i++) {
            ICase casei = new ICase(i);
            liste.add(casei);
            grillePanel.add(casei.getBouton());
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

        contentPanel.add(new JLabel("Tour du joueur : " + joueurs.get(signeCourant)));

        contentPanel.add(grillePanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);


        for (ICase ic : liste) {
            ic.addActionListener(signeCourant);
        }
    }

    
    public void nextTurn(){
        if (signeCourant==Signe.X)
            {signeCourant=Signe.O;}
        else
            {signeCourant=Signe.X;}
        for (ICase ic : getListe()) {
            ic.removeActionListener();
            ic.addActionListener(signeCourant);
        }      
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
