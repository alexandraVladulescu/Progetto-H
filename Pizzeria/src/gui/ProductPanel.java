/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizza;
import data.Pizzeria;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.scene.layout.Border;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
        setPreferredSize(new Dimension(400, 300));
        setBackground(new Color(234, 230, 202));
        setVisible(true);
    }
    
    private void createProductGrid(int n){
        
        pizze = new JButton[n];
        
        for (int i = 0; i < n; i++) {
            Pizza pz = pizzeria.getMenuPizze().getPizze().get(i);
            pizze[i] = new JButton(pz.getName());
            pizze[i].setBackground(new Color(172, 255, 175));
            pizze[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                    pizzeria.getCurrentComanda().addProduct(pz);
                    System.out.println("Pizza Aggiunta: "+pz.getName());
                }
                
            });
            add(pizze[i]);
        }
    }
    
}
