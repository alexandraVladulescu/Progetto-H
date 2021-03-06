/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Francesco
 */
public class Pizza extends Product implements Comparable<Pizza>, Cloneable {

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Ingredient> plusIngredients;

    public Pizza(DescriptionPizza d) throws CloneNotSupportedException {
        //QUANDO ISTANZIO UNA PIZZA GLI PASSO IL DESCRIPTION E FACCIO QUELLO CHE DEVO;
        //SAREBBE MEGLIO DELEGARE QUALCUNO CHE SI OCCUPI DI QUESTE "COPIE"
        super(d.getName(), d.getPrice());
        ingredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient : d.getIngredients()) {
            this.addIngredient((Ingredient) ingredient.clone());
        }

        plusIngredients = new ArrayList<>();

    }

    private Pizza(String name, double price) {// QUESTO SI USA SOLO PER IL CLONE
        super(name, price);
        ingredients = new ArrayList<Ingredient>();
        plusIngredients = new ArrayList<>();
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setPlusIngredients(ArrayList<Ingredient> plusIngredients) {
        this.plusIngredients = plusIngredients;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {// fa una copia degli attributi ma non del riferimento. istanzia un nuovo obj
        Pizza p = new Pizza(this.getName(), this.getPrice());
        for (Ingredient ingredient : this.ingredients) {
            p.ingredients.add((Ingredient) ingredient.clone());
        }
        for (Ingredient ingredient : this.plusIngredients) {
            p.plusIngredients.add((Ingredient) ingredient.clone());
        }
        return (p);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Ingredient> getPlusIngredients() {
        return plusIngredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addPlusIngredient(Ingredient ingredient) {
        plusIngredients.add(ingredient);
    }

    public void addPlusIngredients(ArrayList<Ingredient> plus) {

        this.plusIngredients.addAll(plus);
    }

    public boolean removeIngredient(Ingredient ingredient) {
        return ingredients.remove(ingredient);
    }

    public boolean removePlusIngredient(Ingredient ingredient) {

        return plusIngredients.remove(ingredient);
    }

    @Override
    public int compareTo(Pizza t) {// Oridna in ordine alfabetico a-z
        return this.getName().compareToIgnoreCase(t.getName());
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getFullName() {
        String s = super.getName();
        for (Ingredient ingredient : plusIngredients) {
            s += " + " + ingredient.getName();
        }
        return s;
    }

    @Override
    public double getPrice() {
        double plusPrice = 0;
        for (Ingredient ingredient : plusIngredients) {
            plusPrice += ingredient.getPrice();
        }

        return super.getPrice() + plusPrice;
    }

    public String printIngredient() {
        String t = "";
//        for (Ingredient ingredient : ingredients) {
//            t += "\t" + ingredient.toString() + "\n";
//        }
        for (Ingredient ingredient : plusIngredients) {
            t += "\t***" + ingredient.toString() + "\n";
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
        list1.addAll((ArrayList<Ingredient>) p.getPlusIngredients().clone());
        ArrayList<Ingredient> list2 = (ArrayList<Ingredient>) this.getIngredients().clone();
        list2.addAll((ArrayList<Ingredient>) this.getPlusIngredients().clone());
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

//    public static class ComparatorPizza implements Comparator<Pizza> {
//
//        public ComparatorPizza() {
//        }
//
//        @Override
//        public int compare(Pizza p1, Pizza p2) {
//            ArrayList<Ingredient> tempListIngredient = (ArrayList<Ingredient>) p1.getIngredients().clone();
//            tempListIngredient.addAll((ArrayList<Ingredient>) p1.getPlusIngredients().clone());
//            ArrayList<Ingredient> p1ListCopy = (ArrayList<Ingredient>) p1.getIngredients().clone();
//            p1ListCopy.addAll((ArrayList<Ingredient>) p1.getPlusIngredients().clone());
//            ArrayList<Ingredient> p2ListCopy = (ArrayList<Ingredient>) p2.getIngredients().clone();
//            p2ListCopy.addAll((ArrayList<Ingredient>) p2.getPlusIngredients().clone());
//
//            p1ListCopy.removeAll(p2ListCopy);// rimuovo dalla lista copiata di p1
//            p2ListCopy.removeAll(tempListIngredient); // rimuovo dalla lista copiata di p2
//            if (p1ListCopy.size() > p2ListCopy.size()) {
//                return 1;
//            } else if (p1ListCopy.size() < p2ListCopy.size()) {
//                return -1;
//            } else {
//                if (p1.getPrice() > p2.getPrice()) {
//                    return 1;
//                } else if (p1.getPrice() < p2.getPrice()) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//
//    }
}
