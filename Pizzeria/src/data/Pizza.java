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

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Pizza copyPizza() {// fa una copia degli attributi ma non del riferimento. istanzia un nuovo obj
        Pizza p = new Pizza(this.getName(), this.getPrice());
        p.setIngredients((ArrayList<Ingredient>) this.getIngredients().clone());
        return (p);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
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
        double plusPrice = 0;
        for (Ingredient ingredient : ingredients) {
            plusPrice += ingredient.getPrice();
        }

        return super.getPrice() + plusPrice; //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean equals(Object o) {
        Pizza p = (Pizza) o;
        ArrayList<Ingredient> list1 = (ArrayList<Ingredient>) p.getIngredients().clone();
        ArrayList<Ingredient> list2 = (ArrayList<Ingredient>) this.getIngredients().clone();
        if (list1.size() == list2.size()) {
            list1.removeAll(list2);
            if (list1.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

}
