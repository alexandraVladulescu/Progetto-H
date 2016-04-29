/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import data.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class OrderDetailsPanel extends JPanel {

    private Pizzeria pizzeria;
    
    private ClientDetailsPanel clientPanel;
    private ProductDetailPanel prDtPanel;
    
    public OrderDetailsPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        
        clientPanel = new ClientDetailsPanel();
        prDtPanel =  new ProductDetailPanel(pizzeria);
        
        setLayout(new GridLayout(2, 1));
        setBackground(new Color(234, 230, 202));
        setPreferredSize(new Dimension(400, 300));
        setVisible(true);
        
        add(clientPanel);
        add(prDtPanel);
    }

    public void update() {
        prDtPanel.removeAll();
        repaint();
        for (Product  p: pizzeria.getCurrentComanda().getOrdersList()) {
            prDtPanel.createProductLine(p);
        }
    }

}
    