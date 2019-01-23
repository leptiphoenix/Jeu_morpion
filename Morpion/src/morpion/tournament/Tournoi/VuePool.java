/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.Message;
import morpion.tournament.Partie.Message.MessageJeu;

/**
 *
 * @author deniaul
 */
public class VuePool extends Observable {

    private JFrame window;
    private JButton btnRegles;
    private JButton btnScoreGeneral;
    private JButton btnJouer;
    private PanelPerso topPanel = new PanelPerso(new GridLayout(2, 1));
    private PanelPerso botPanel = new PanelPerso(new GridLayout(2, 1));
    private PanelPerso closePanel;
    private PanelPerso infosPanel;
    private PanelPerso borderPanel;
    private PanelPerso poolpanel;
    private PanelPerso selectionPanel;
    private PanelPerso mainPanel;
    private Border blackline = BorderFactory.createLineBorder(Color.black, 1);
    private PhaseDePool pdp;
    private ArrayList<JCheckBox> listeBoites = new ArrayList<JCheckBox>();

    @SuppressWarnings("Convert2Lambda")
    public VuePool(PhaseDePool pp) {
        pdp = pp;
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1200, 200 + 50 * pdp.getListePools().size());
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        mainPanel = new PanelPerso(new BorderLayout());
        window.add(mainPanel);

        closePanel = new PanelPerso(new GridLayout(1, 2));
        closePanel.add(new JLabel("phase de pool : " + pdp.getNbPhase() + " sur 9"));
        closePanel.add(new JLabel("Fermer cette fenêtre annule le tournoi"));
        topPanel.add(closePanel);

        infosPanel = new PanelPerso(new GridLayout(1, 5));
        infosPanel.add(new JLabel("Joueur"));
        infosPanel.add(new JLabel("Score de pool"));
        infosPanel.add(new JLabel("Victoires/Défaites/Egalités"));
        infosPanel.add(new JLabel("Parties restantes"));
        casev(infosPanel);
        topPanel.add(infosPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        borderPanel = new PanelPerso(new GridLayout(pdp.getListePools().size(), 1));
        //A RAJOUTER DES PANELS DE POOLS DANS CHAQUE BORDERPANEL
        for (int i = 0; i < pdp.getListePools().size(); i++) {
            poolpanel = new PanelPerso(new GridLayout(pdp.getListePools().get(i).getJoueurs().size(), 5));
            poolpanel.setBorder(blackline);
            for (int j = 0; j < pdp.getListePools().get(i).getJoueurs().size(); j++) {
                Participant par = pdp.getListePools().get(i).getJoueurs().get(j);
                poolpanel.add(new JLabel(par.getSurnom()));
                poolpanel.add(new JLabel(String.valueOf(par.getScorePool().getPoints())));
                poolpanel.add(new JLabel(par.getScorePool().toString()));
                poolpanel.add(new JLabel(String.valueOf(pdp.nbPartiesRestantes(par))));
                JCheckBox boite = new JCheckBox();
                listeBoites.add(boite);
                boite.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        switch (boitesCochées().size()) {
                            case 0:
                                actualiser(true);
                                break;
                            case 1:
                                actualiser(pdp.getTournoi().getListeParticipants().get(listeBoites.indexOf(boitesCochées().get(0))));
                                break;
                            case 2:
                                actualiser(false);
                                break;
                        }
                    }
                });
                poolpanel.add(boite);
            }
            borderPanel.add(poolpanel);
        }
        mainPanel.add(borderPanel, BorderLayout.CENTER);

        selectionPanel = new PanelPerso(new GridLayout(1, 2));
        casev(selectionPanel);
        selectionPanel.add(new JLabel("Sélectionner deux joueurs prêt à jouer"));
        botPanel.add(selectionPanel);
        PanelPerso bottomPanel = new PanelPerso(new GridLayout(1, 7));
        casev(bottomPanel);

        btnRegles = new JButton(new ImageIcon(new ImageIcon("src/morpion/images/regles.jpg").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        btnRegles.setText("Règles du jeu");
        btnRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.REGLES));
                clearChanged();
            }
        });
        bottomPanel.add(btnRegles);

        casev(bottomPanel);

        btnScoreGeneral = new JButton(new ImageIcon(new ImageIcon("src/morpion/images/scoreGeneral.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnScoreGeneral.setText("Score général");
        btnScoreGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.AFFICHESCORE));
                clearChanged();
            }
        });
        bottomPanel.add(btnScoreGeneral);

        casev(bottomPanel);

        
        btnJouer = new JButton(new ImageIcon(new ImageIcon("src/morpion/images/jouer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnJouer.setText("Jouer");
        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (boitesCochées().size()==2){
                    setChanged();
                    notifyObservers(new MessageJeu(Action.JOUE,
                            pdp.getTournoi().getListeParticipants().get(listeBoites.indexOf(boitesCochées().get(0))),
                            pdp.getTournoi().getListeParticipants().get(listeBoites.indexOf(boitesCochées().get(1)))
                    ));
                    clearChanged();
                    ((JLabel)selectionPanel.getComponent(1)).setText("Partie en cours");
                }
                else{
                    selectionPanel.getComponent(1).setForeground(Color.red);
                }
            }
        });
        bottomPanel.add(btnJouer);

        casev(bottomPanel);
        botPanel.add(bottomPanel);
        mainPanel.add(botPanel, BorderLayout.SOUTH);

        actualiser(true);
    }

    public void actualiserBoite(Participant p, Component boite, Boolean b) {
        if (pdp.nbPartiesRestantes(p) > 0 && b) {
            boite.setEnabled(true);
        } else {
            boite.setEnabled(false);
        }
    }
    public ArrayList<JCheckBox> boitesCochées(){
        ArrayList<JCheckBox> boites = new ArrayList<>();
                        for (JCheckBox b: listeBoites){
                            if (b.isSelected()){
                                boites.add(b);
                            }
                        }
                        return boites;
    }
    
    public void actualiser(Boolean b) {
        for (Participant pa : pdp.getTournoi().getListeParticipants()) {
            if (!(listeBoites.get(pdp.getTournoi().getListeParticipants().indexOf(pa)).isSelected())){
              actualiserBoite(pa,listeBoites.get(pdp.getTournoi().getListeParticipants().indexOf(pa)) , b);  
            }
        }
    }
    
    public void actualiser(Participant p) {
        for (Participant pa : pdp.getTournoi().getListeParticipants()) {
//Une autre manière de faire, compliquée, qui marche, mais très long à écrire et débugguer..... Ca m'apprendras à ne pas prendre de recul
            
//            for (Pool po : pdp.getListePools()) {
//                int posPool = pdp.getListePools().indexOf(pdp.poolDuJoueur(pa));
//                int posJoueurDansPool;
//                if (pdp.getTournoi().getListeParticipants().size() % 3 == 2
//                        && pdp.getTournoi().getListeParticipants().get(pdp.getTournoi().getListeParticipants().size() - 3) == pa) {
//                    posJoueurDansPool = 0;
//                } else {
//                    posJoueurDansPool = pdp.getTournoi().getListeParticipants().indexOf(pa) % 3;
//                }
//                PanelPerso pp = (PanelPerso) mainPanel.getComponent(1);
//                PanelPerso pp2 = (PanelPerso) pp.getComponent(posPool);
//                if (pdp.participantsDeLaPoolDe(p).contains(pa)) {
//                    actualiserBoite(pa, pp2.getComponent(5 * (posJoueurDansPool + 1) - 1), true);
//                } else {
//                    actualiserBoite(pa, pp2.getComponent(5 * (posJoueurDansPool + 1) - 1), false);
//                }
//
//            }
            if (pdp.participantsDeLaPoolDe(p).contains(pa)) {
                actualiserBoite(pa,listeBoites.get(pdp.getTournoi().getListeParticipants().indexOf(pa)) , true);
            }
            else{
                actualiserBoite(pa,listeBoites.get(pdp.getTournoi().getListeParticipants().indexOf(pa)) , false);
            }
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
