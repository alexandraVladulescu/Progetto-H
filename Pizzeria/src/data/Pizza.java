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
public class Pizza extends Product implements Comparable<Pizza> {

    private ArrayList<Ingredient> ingredients;

    public Pizza(String name, double price) {
        super(name, price);
        ingredients = new ArrayList<Ingredient>();

    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public int compareTo(Pizza t) {// Oridna in ordine alfabetico a-z
        return this.getName().compareToIgnoreCase(t.getName());
    }

    @Override
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPrice() {
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    public String printIngredient() {
        String t = "";
        for (Ingredient ingredient : ingredients) {
            t += "\t" + ingredient.toString() + "\n";
        }
        return t;
    }

    @Override
    public String toString() {
        return "\t" + getName() + "\t" + getPrice() + "\n" + this.printIngredient() + "\n";
    }

}
