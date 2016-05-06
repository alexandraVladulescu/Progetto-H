/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Ingredient;
import data.IngredientsManager;
import exceptions.IngredientNotFoundException;

/**
 *
 * @author Francesco
 */
public class IngredientsManagerTests {

    public static void main(String[] args) {
        IngredientsManager i = IngredientsManager.getInstance();
        i.addIngredient(new Ingredient("acciuga", 0.5));
        i.addIngredient(new Ingredient("wurstel", 1));
        i.addIngredient(new Ingredient("patatine", 1.5));
        i.addIngredient(new Ingredient("salmone", 2));

        try {
            i.getIngredientByName("acciuga");
            i.getIngredientByName("olive");
           

        } catch (IngredientNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\tTUTTI GLI INGREDIENTI AGGIUNTI NEL DB:\n");
        System.out.println(i.printAllIngredient());

    }
}
