/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public class Pizza extends Product {

    private ArrayList<Ingredient> ingredients;
    
    public Pizza(String name, double price) {
        super(name, price);

    }
    
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    
    

}
