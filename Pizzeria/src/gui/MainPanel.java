/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class MainPanel extends JPanel{

    private Pizzeria pizzeria; 
    
    private ProductPanel prPanel;
    private ComandaPanel cmPanel;
    
    public MainPanel(){
        
        try {
            pizzeria = new Pizzeria();
            
            pizzeria.loadMenues();
            
            prPanel = new ProductPanel(pizzeria);
            cmPanel = new ComandaPanel(pizzeria);
            
            setLayout(new BorderLayout());
            setBackground(java.awt.Color.BLACK);
            
            add(prPanel, BorderLayout.WEST);
            add(cmPanel, BorderLayout.EAST);
            
        } catch (IOException ex) {
            //JDialog errorDialog = new JDialog
            System.err.println("Problemi di Input Dei File");
        }
        
    }
    
    
}
