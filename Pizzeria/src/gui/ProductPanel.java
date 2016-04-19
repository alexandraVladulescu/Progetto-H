/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ProductPanel extends JPanel{

    public ProductPanel() {
        
        setLayout(new GridLayout(8, 8));
        setBackground(Color.yellow);
        setVisible(true);
    }
    
    
}
