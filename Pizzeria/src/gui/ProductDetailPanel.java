/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import data.Product;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ProductDetailPanel extends JPanel {
    
    private Pizzeria pizzeria;
    private HashMap<JButton, ProductLineView> button_map;

    public ProductDetailPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        button_map = new HashMap<>();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
    }
    
    public void createProductLine(Product p){
        ProductLineView plv = new ProductLineView(p);
        add(addProductLineView(plv));
    }
    
    public HashMap<JButton, ProductLineView> getButton_map(){
        return button_map;
        
    }

    public void removeAll() {
        for (Iterator iterator = button_map.keySet().iterator(); iterator.hasNext();) {
            button_map.remove(iterator.next());
        }
    }
    
    private JButton addProductLineView(ProductLineView plv){
        JButton deleteButton = new JButton("delete");
        
        button_map.put(deleteButton, plv);
        
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                removeProductLine(deleteButton);
            }
    });
        return deleteButton;
    }
    
    private void removeProductLine(JButton delButton){
        button_map.remove(delButton);
        System.out.println("ci vado qua ?");
        repaint();
    }
    
}
