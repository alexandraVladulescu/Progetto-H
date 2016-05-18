/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.Comanda;
import data.CurrentComandaManager;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class PizzaSimileTest {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException, ComandaNotFoundException {
        Pizzeria pizzeria = new Pizzeria();
 CurrentComandaManager c = pizzeria.getCurrentComandaManager();
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt",FormatType.TXT);

        System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + pizzeria.printAllIngredients());

        c.setCurrentComanda(new Comanda());
        c.setClientToComanda(new Client("Paperino", "bho", "0923 432665", new Address("Milano", "vialeZara", "33/b")));
        c.addPizza("margherita");
        c.confirmComanda();
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());

        c.addIngredientToPizza("acciughe", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());

        c.addIngredientToPizza("origano", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());

        c.addIngredientToPizza("capperi", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
         //System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());

        c.addIngredientToPizza("carciofi", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());

        c.addPizza("margherita");
        c.addIngredientToPizza("prosciutto cotto", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
        c.addIngredientToPizza("funghi", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
        c.addIngredientToPizza("salsiccia", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());

        c.removePizza(0);
         System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
         
         c.addPizza("margherita");
      c.addIngredientToPizza("patatine", 1);
       System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
      
       c.addPizza("margherita");
       c.addIngredientToPizza("mozzarella", 2);
         System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
    }
}
