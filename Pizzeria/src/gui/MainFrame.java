/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import oracle.jrockit.jfr.JFR;

/**
 *
 * @author User
 */
public class MainFrame {
    
    public static void main(String[] args) {
        
        OrderView panel = new OrderView();
        
        JTabbedPane selectView = new JTabbedPane();
        selectView.addTab("Crea Nuovo Ordine", panel);
        
        JFrame fr = new JFrame("Pizzeria");
        //fr.setSize(700, 700);
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.add(selectView);
        fr.setVisible(true);
    }
    
}
