/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Pizzeria;
import data.Product;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author User
 */
public class ProductDetailPanel extends JPanel{
    
    private ArrayList<ProductLineView> productsDetailsList;
    
    private Pizzeria pizzeria;

    public ProductDetailPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        
        productsDetailsList = new ArrayList<>();
    }
    
    public void createProductLine(Product p){
        productsDetailsList.add(new ProductLineView(p));
        add(productsDetailsList.get(productsDetailsList.size()-1));
    }

    public ArrayList<ProductLineView> getProductsDetailsList() {
        return productsDetailsList;
    }
    
    public void removeAll(){
        for (ProductLineView productLineView : productsDetailsList) {
            productLineView.removeAll();
        }
    }
    
}
