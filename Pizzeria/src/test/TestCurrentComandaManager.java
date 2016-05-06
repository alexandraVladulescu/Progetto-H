/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Client;
import data.CurrentComandaManager;
import data.Pizzeria;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class TestCurrentComandaManager {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException, ProductNotFoundException, IngredientNotFoundException {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);
        CurrentComandaManager c = pizzeria.getCurrentComandaManager();

        System.out.println("********MENUPIZZA" + pizzeria.printMenuPizze());

        c.createComanda();
        
        c.addPizza("Bismark");

        c.addPizza("Bismark");
        
        c.addPizza("Bismark");
        
        c.addPizza("Bismark");
        
        c.setClientToComanda(new Client("Giuseppe", "Rossi","0982345894",null ));
        System.out.println(c.showComandaDetails());
        System.out.println(pizzeria.getMenuPizze().getPizzaByName("Bismark").getIngredients().toString()); 
        
        c.removeIngredientToPizza("prosciutto cotto", 1);
          System.out.println(c.showComandaDetails());
        
        c.removeIngredientToPizza("uovo", 3);
         System.out.println(c.showComandaDetails());
        
        
    }
}
