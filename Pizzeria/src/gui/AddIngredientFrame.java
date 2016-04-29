/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.IngredientsManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class AddIngredientFrame extends JDialog{

    private IngredientsManager ingredientManager;
    private ArrayList<JLabel> ingredientLabelList;
    
    public AddIngredientFrame() {
        ingredientManager = new IngredientsManager();
        ingredientLabelList = new ArrayList<>();
        
        try {
            ingredientManager.loadMenu("./src/databases/ingredienti.txt");
        } catch (IOException ex) {
            Logger.getLogger(AddIngredientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < ingredientManager.getIngredients().size(); i++) {
            ingredientLabelList.add(new JLabel(""+ingredientManager.getIngredients().get(i).getName()));
        }
        
        setTitle("add Ingredient");
    }
    
//    public Ingredient addIngredient(){
//        
//    }
            
}
