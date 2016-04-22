/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.prism.j2d.J2DPipeline;
import data.Product;
import java.awt.GridLayout;
import javafx.scene.control.Spinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ProductLineView extends JPanel{
    
    private Product product;
    
    private JLabel name;
    private JLabel price;
    private JButton edit;
    private JButton delete;

    public ProductLineView(Product product) {
        this.product=product;
        
        name=new JLabel();
        price=new JLabel();
        edit=new JButton("Edit");
        delete=new JButton("Delete");
        
        setLayout(new GridLayout(2, 2));
        
        add(name);
        add(price);
        add(edit);
        add(delete);
    }
    
    
    
}
