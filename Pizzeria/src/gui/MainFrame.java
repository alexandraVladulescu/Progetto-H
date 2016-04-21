/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class MainFrame {
    
    public static void main(String[] args) {
        
        MainPanel panel = new MainPanel();
        
        JFrame fr = new JFrame("Pizzeria");
        fr.setSize(600, 600);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.add(panel);
        fr.setVisible(true);
    }
    
}
