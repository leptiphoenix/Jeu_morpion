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
import java.util.Hashtable;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import morpion.tournament.Partie.Message.Action;
import morpion.tournament.Partie.Message.Message;
import morpion.tournament.Partie.Message.MessageParticipant;

/**
 *
 * @author deniaul
 */
public class VueModif extends Observable{
    private final JFrame window ;
    private final JTextField champSurnom;
    private final JButton btnValider ;
    private final JButton btnAnnuler ;
    private JLabel msgErreur = new JLabel("");
    private PanelPerso erreurPanel = new PanelPerso();
    private JSlider pref; 
    private PanelPerso mainPanel;
    private JLabel age;
    private PanelPerso agePanel;
    private PanelPerso inputPanel;
    private PanelPerso boutonsPanel;
    private PanelPerso closePanel;
    
    
    @SuppressWarnings("Convert2Lambda")
    public VueModif(String clé, String surnomParDefaut, int prefParDefaut) {
        
        window = new JFrame();
        // Définit la taille de la fenêtre en pixels
        window.setSize(550, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        mainPanel = new PanelPerso(new BorderLayout());
        mainPanel.setOpaque(true);
        window.add(mainPanel);
        
        
   
        PanelPerso contentPanel = new PanelPerso (new GridLayout(4, 1));
        contentPanel.setOpaque(false);
        
        inputPanel = new PanelPerso(new GridLayout(1,3));
        
        champSurnom = new JTextField();
        champSurnom.setText(surnomParDefaut);
        
        closePanel = new PanelPerso(new GridLayout(1,2));
        casev(closePanel);
        closePanel.add(new JLabel("Fermer cette fenêtre annule la saisie"));
        
        
        agePanel = new PanelPerso(new GridLayout(1, 3));
            
        
        PanelPerso sliderPanel = new PanelPerso();
        pref = new JSlider(JSlider.HORIZONTAL,1,3,prefParDefaut);
        pref.setMinorTickSpacing(1);
        pref.setMajorTickSpacing(3);
        pref.setPaintTicks(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put(1,new JLabel("Jeune"));
        labelTable.put(2,new JLabel("Adulte"));
        labelTable.put(3,new JLabel("Agé"));
        pref.setLabelTable(labelTable);
        pref.setPaintLabels(true);
        pref.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event) {
                actualiserPref();
        }
        });
        sliderPanel.add(pref);
        
        
        contentPanel.add(closePanel,BorderLayout.NORTH);
        contentPanel.add(inputPanel);
        contentPanel.add(agePanel);
        contentPanel.add(sliderPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        //création du panel erreur
        erreurPanel.add(msgErreur);
        
        //création du panel des boutons 
        boutonsPanel = new PanelPerso(new GridLayout(1,3));
             
        //création du bouton valider
        btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new MessageParticipant(Action.VALIDE,clé, champSurnom.getText(),pref.getValue()));
                System.out.println(pref.getValue());
                clearChanged();
            }
        });
        
        
        
         //création du bouton annuler
        btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.ANNULE));
                clearChanged();
            }
        });
        
        //ajout des boutons
        boutonsPanel.add(btnAnnuler);
        casev(boutonsPanel);
        boutonsPanel.add(btnValider); 
        //actualisation de l'erreurPanel
        actualiser(false);
        //création du bottomPanel
        PanelPerso bottomPanel = new PanelPerso(new GridLayout(2,1));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); 
        bottomPanel.add(erreurPanel);
        bottomPanel.add(boutonsPanel);
        actualiserPref();
        

        btnValider.setBorderPainted(false);
        btnAnnuler.setBorderPainted(false);
    }
    
    
    public void actualiserPref(){

        agePanel.removeAll();
        inputPanel.removeAll();
        boutonsPanel.removeAll();
        closePanel.removeAll();
                
        
        switch (pref.getValue()) {
            case 1:
                mainPanel.setBackground(Color.orange);
                pref.setBackground(Color.CYAN); 
                btnValider.setBackground(Color.GREEN);
                btnAnnuler.setBackground(Color.red);
                btnValider.setForeground(Color.white);
                btnAnnuler.setForeground(Color.white);
                inputPanel.add(new JLabel("Ton pseudo :"));
                age = new JLabel("Tu es : ");
                boutonsPanel.add(btnAnnuler);
                casev(boutonsPanel);
                boutonsPanel.add(btnValider);
                casev(closePanel);
                casev(closePanel);
                break;
            case 2:
                mainPanel.setBackground(Color.white);
                pref.setBackground(Color.white); 
                btnValider.setBackground(Color.BLUE);
                btnAnnuler.setBackground(Color.BLUE);
                btnValider.setForeground(Color.white);
                btnAnnuler.setForeground(Color.white);
                inputPanel.add(new JLabel(""));
                age = new JLabel("");
                casev(boutonsPanel);
                btnValider.setLabel("Continuer");
                boutonsPanel.add(btnValider);
                casev(boutonsPanel);
                casev(closePanel);
                casev(closePanel);
                break;
            case 3:
                mainPanel.setBackground(Color.PINK);
                pref.setBackground(Color.LIGHT_GRAY); 
                btnValider.setBackground(null);
                btnAnnuler.setBackground(null);
                btnValider.setForeground(Color.black);
                btnAnnuler.setForeground(Color.black);
                inputPanel.add(new JLabel("Votre surnom :"));
                age = new JLabel("Vous êtes :");
                boutonsPanel.add(btnAnnuler);
                casev(boutonsPanel);
                boutonsPanel.add(btnValider);
                casev(closePanel);
                closePanel.add(new JLabel("Fermer cette fenêtre annule la saisie"));
                break;
        }
        inputPanel.add(champSurnom);
        casev(inputPanel);
        agePanel.add(age);
        casev(agePanel);
        casev(agePanel);
        afficher();
        
        
    }
    
    
    
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    //actualise le bottomPanel pour afficher les messages d'erreur
    public void actualiser(Boolean e) {
        erreurPanel.removeAll();
        //il y'a une erreur
        if (e){      
            msgErreur = new JLabel("Le surnom est déjà pris par un autre joueur !");
            msgErreur.setForeground(Color.red);            
        }
        erreurPanel.add(msgErreur);
        this.afficher();                
    }
    
    //crée ne case vide
    private void casev(PanelPerso panel){
        panel.add(new JLabel(""));
    }
    
    
    
    void close() {
        this.window.dispose();
    }
}
