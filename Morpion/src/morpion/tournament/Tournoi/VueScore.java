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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author deniaul
 */
public class VueScore  extends Observable{
    private JFrame window;
    private JButton btnSuivant;
    private PanelPerso topPanel;
    private PanelPerso detailsPanel;
    private PanelPerso contentPanel;
    private PanelPerso borderPanel;
    private String[] listetri={"par score","par surnom"};
    private JComboBox tri = new JComboBox(listetri);
    private Border blackline=BorderFactory.createLineBorder(Color.black,1);

    @SuppressWarnings("Convert2Lambda")
    public VueScore(ArrayList<Participant> participants) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(400,100 + participants.size()*25);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        contentPanel = new PanelPerso(new BorderLayout());
        window.add(contentPanel);

        
        
        topPanel = new PanelPerso(new GridLayout(2,3));
        casev(topPanel);
        topPanel.add(new JLabel("Trier par :"));
        topPanel.add(tri);
           
            
        topPanel.add(new JLabel("Surnom"));
        topPanel.add(new JLabel("Score"));
        casev(topPanel);
        contentPanel.add(topPanel,BorderLayout.NORTH);
        
        
        borderPanel = new PanelPerso(new GridLayout(participants.size(),1));
        borderPanel.setBorder(blackline);
        for (Participant p : participants){
            detailsPanel = new PanelPerso(new GridLayout(1,3));
            detailsPanel.add(new JLabel(p.getSurnom()));
            detailsPanel.add(new JLabel(p.getScoreTournoi().toString()));
            JButton details = new JButton("+ de details");
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
        contentPanel.add(borderPanel,BorderLayout.CENTER);
   
        PanelPerso bottomPanel = new PanelPerso(new GridLayout(1, 7));
        
        casev(bottomPanel);
         
        btnSuivant = new JButton("retour");
        btnSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        bottomPanel.add(btnSuivant);

        casev(bottomPanel);

   
        
        contentPanel.add(bottomPanel,BorderLayout.SOUTH);
        
        

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

