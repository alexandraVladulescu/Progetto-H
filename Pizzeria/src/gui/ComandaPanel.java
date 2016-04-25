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
import java.util.Observer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class ComandaPanel extends JPanel{
    
    private Pizzeria pizzeria;
    
    private JTextArea comandaTextArea;

    public ComandaPanel(Pizzeria pizzeria){
        
       this.pizzeria=pizzeria;
        
        setBackground(new Color(234, 230, 202));
        
        comandaTextArea = new JTextArea();
        
        add(comandaTextArea);
        
        createComanda();
    }
    
    public void createComanda(){
        pizzeria.setCurrentComanda(new Comanda());
        pizzeria.setClientToComanda(new Client("Zio", "Simo","3337736974", new Address("MI", "Città del Fumo", "AK47")));
        comandaTextArea.setText(pizzeria.showComandaDetails());
    }

    public void update(){
        comandaTextArea.setText(pizzeria.showComandaDetails());
    }
    
}
