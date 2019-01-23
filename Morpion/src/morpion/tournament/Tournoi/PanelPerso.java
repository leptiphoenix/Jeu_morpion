/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion.tournament.Tournoi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author deniaul
 */
public class PanelPerso extends JPanel{
    public PanelPerso(){
        super();
        setOpaque(false);
   }

    public PanelPerso(BorderLayout borderLayout) {
        super(borderLayout);
        setOpaque(false);
    }
    public PanelPerso(GridLayout gridLayout){
        super(gridLayout);
        setOpaque(false);
    }
}
