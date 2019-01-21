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
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import morpion.tournament.Partie.Message.*;

/**
 *
 * @author deniaul
 */
public class VueSelection extends Observable {

    private final JFrame window;
    private final JButton btnAjouter;
    private final JButton btnCommencer;
    private PanelPerso contentPanel = new PanelPerso();
    private PanelPerso bottomPanel;
    private Dimension dim;

    @SuppressWarnings("Convert2Lambda")
    public VueSelection(ArrayList<Participant> participants) {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels

        window.setResizable(false);
        
        PanelPerso mainPanel = new PanelPerso(new BorderLayout());
        window.add(mainPanel);

        
        
        PanelPerso closePanel = new PanelPerso(new GridLayout(2,2));
        mainPanel.add(closePanel,BorderLayout.NORTH);
        casev(closePanel);
        closePanel.add(new JLabel("Fermer cette fenêtre annule le tournoi"));
        closePanel.add(new JLabel("Participants :"));
        casev(closePanel);

        actualiser(participants);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

       bottomPanel = new PanelPerso(new GridLayout(1, 3));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.AJOUTE));
                clearChanged();
            }
        });
        bottomPanel.add(btnAjouter);

        casev(bottomPanel);

        btnCommencer = new JButton("Commencer");
        btnCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.COMMENCE));
                clearChanged();
            }
        });
        bottomPanel.add(btnCommencer);

    }

    public void actualiser(ArrayList<Participant> participants) {
        getContentPanel().removeAll();
        if (participants.size() > 0) {
            getWindow().resize(1000, 100 + 50*participants.size());
            getContentPanel().setLayout(new GridLayout(participants.size(), 3));

            for (int i = 0; i < participants.size();i++) {
                getContentPanel().add(new JLabel(participants.get(i).getSurnom()));
                int clé = i;
                JButton btn = new JButton("Modifier");
                btn.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(new MessageCle(Action.MODIFIE, clé));
                    clearChanged();
                });
                getContentPanel().add(btn);
                JButton btns = new JButton("Supprimer");
                btns.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(new MessageCle(Action.SUPPRIME, clé));
                    clearChanged();
                });
                getContentPanel().add(btns);
            }
        } else {
            getWindow().resize(1000, 150);
            casev(getContentPanel());
        }
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
    }
    
    
    public void msgErreur(){
        ((JLabel) getBottomPanel().getComponent(1)).setText("Deux participants au moins sont nécessaire");
        ((JLabel) getBottomPanel().getComponent(1)).setForeground(Color.red);
        afficher();
    }

    public void afficher() {
        this.getWindow().setVisible(true);
    }

    private void casev(PanelPerso panel) {
        panel.add(new JLabel(""));
    }

    void close() {
        this.getWindow().dispose();
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the btnAjouter
     */
    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    /**
     * @return the btnCommencer
     */
    public JButton getBtnCommencer() {
        return btnCommencer;
    }

    /**
     * @return the contentPanel
     */
    public PanelPerso getContentPanel() {
        return contentPanel;
    }

    /**
     * @return the bottomPanel
     */
    public PanelPerso getBottomPanel() {
        return bottomPanel;
    }
}
