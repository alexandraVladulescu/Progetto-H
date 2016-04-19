/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ComandaPanel extends JPanel {

    private Pizzeria pizzeria;
    
    public ComandaPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        
        setBackground(Color.cyan);
        setVisible(true);
    }

}
    