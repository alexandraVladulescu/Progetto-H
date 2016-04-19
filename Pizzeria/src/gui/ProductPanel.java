/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizza;
import data.Pizzeria;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ProductPanel extends JPanel{

    private Pizzeria pizzeria;
    
    private JButton[] pizze;
    //private JButton[] bevande;
    
    
    public ProductPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        
        int n = pizzeria.getMenuPizze().getMenuSize();
        
        int lineNumber = (int)(n/(int)Math.sqrt(n))+1;
        
        createProductGrid(n-1);
        
        setLayout(new GridLayout(lineNumber, lineNumber-1));
        setBackground(Color.yellow);
        setVisible(true);
    }
    
    private void createProductGrid(int n){
        
        pizze = new JButton[n];
        
        for (int i = 0; i < n; i++) {
            pizze[i] = new JButton(pizzeria.getMenuPizze().getPizze().get(i).getName());
            add(pizze[i]);
        }
    }
    
}
