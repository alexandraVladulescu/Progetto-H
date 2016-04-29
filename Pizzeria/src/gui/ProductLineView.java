/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Ingredient;
import data.Pizza;
import data.Product;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Action;
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

    public ProductLineView(Product product) {
        this.product=product;
        
        name=new JLabel(product.getName());
        price=new JLabel(""+product.getPrice());
        edit=new JButton("Edit");
                
        setEditFunctionality();
        
        setLayout(new GridLayout(2, 2));
        
        add(name);
        add(price);
        add(edit);
    }

    private void setEditFunctionality() {
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                editPizza();
            }
            
});
    }

    private void editPizza(){
        Pizza pz = (Pizza)product;
        //pz.addPlusIngredient(Ingredient ing);
        AddIngredientFrame aif = new AddIngredientFrame();
        aif.setVisible(true);
    }
    
}
