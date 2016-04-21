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
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class ComandaPanel extends JPanel {

    private Pizzeria pizzeria;
    
    private JTextArea comandaTextArea;
    
    public ComandaPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        
        comandaTextArea = new JTextArea();
        
        setBackground(new Color(234, 230, 202));
        setPreferredSize(new Dimension(300, 200));
        setVisible(true);
        
        createComanda();
        
        add(comandaTextArea);
    }
    
    public void createComanda(){
        pizzeria.setCurrentComanda(new Comanda());
        pizzeria.setClientToComanda(new Client("Zio", "Simo","3337736974", new Address("MI", "Città del Fumo", "AK47")));
        comandaTextArea.setText(pizzeria.showComandaDetails());
    }

}
    