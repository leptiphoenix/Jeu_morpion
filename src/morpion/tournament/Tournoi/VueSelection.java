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
import java.util.HashMap;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import morpion.tournament.Partie.Message.*;

/**
 *
 * @author deniaul
 */
public class VueSelection extends Observable {

    private final JFrame window;
    private final JButton btnAjouter;
    private final JButton btnCommencer;
    PanelPerso contentPanel = new PanelPerso();

    @SuppressWarnings("Convert2Lambda")
    public VueSelection(HashMap<String, Participant> participants) {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(600, 150);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

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

        PanelPerso bottomPanel = new PanelPerso(new GridLayout(1, 3));
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

    public void actualiser(HashMap<String, Participant> participants) {
        contentPanel.removeAll();
        if (participants.size() > 0) {
            window.resize(600, 100 + 50*participants.size());
            contentPanel.setLayout(new GridLayout(participants.size(), 3));

            for (String cle : participants.keySet()) {
                contentPanel.add(new JLabel(participants.get(cle).getSurnom()));
                JButton btn = new JButton("Modifier");
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(new MessageCle(Action.MODIFIE, cle));
                        clearChanged();
                    }
                });
                contentPanel.add(btn);
                JButton btns = new JButton("Supprimer");
                btns.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(new MessageCle(Action.SUPPRIME, cle));
                        clearChanged();
                    }
                });
                contentPanel.add(btns);
            }
        } else {
            window.resize(600, 150);
            casev(contentPanel);
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
