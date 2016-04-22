/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Address;
import data.Client;
import data.Comanda;
import data.Pizzeria;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class OrderDetailsPanel extends JPanel {

    private Pizzeria pizzeria;
    
    private ClientDetailsPanel clientPanel;
    
    public OrderDetailsPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        
        clientPanel = new ClientDetailsPanel();
        
        setLayout(new GridLayout(2, 1));
        setBackground(new Color(234, 230, 202));
        setPreferredSize(new Dimension(400, 300));
        setVisible(true);
        
        add(clientPanel);
    }

}
    