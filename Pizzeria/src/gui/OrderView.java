/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import i_o_V1.FormatType;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author User
 */
public class OrderView extends JPanel implements Observer{

    private Pizzeria pizzeria; 
    
    private ProductPanel pizzePanel;
    private OrderDetailsPanel dtPanel;
    private ComandaPanel cmPanel;
    
    private JTabbedPane productTypePane;
   
    
    public OrderView(){
        
        productTypePane = new JTabbedPane();
        productTypePane.setBackground(new java.awt.Color(175, 255, 172));
           
        try {
            pizzeria = new Pizzeria();
            
            pizzeria.loadMenuPizza("./databases/MenuPizze.xml", FormatType.XML);
            
            pizzePanel = new ProductPanel(pizzeria);
            dtPanel = new OrderDetailsPanel(pizzeria);
            cmPanel = new ComandaPanel(pizzeria);
            
            pizzeria.getCurrentComanda().addObserver(this);
            
            productTypePane.addTab("Pizze", pizzePanel);
            
            setLayout(new GridLayout(1, 3));
            setBackground(new java.awt.Color(195, 176, 145));
            
            add(productTypePane);
            add(dtPanel);
            add(cmPanel);
            
        } catch (IOException ex) {
            //JDialog errorDialog = new JDialog
            System.err.println("Problemi di Input Dei File");
        }
        
    }

    @Override
    public void update(Observable o, Object o1) {
        cmPanel.update();
        dtPanel.update();
        repaint();
        
    }
    
    
}
