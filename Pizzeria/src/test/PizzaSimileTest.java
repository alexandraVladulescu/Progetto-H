/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.Comanda;
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
public class PizzaSimileTest {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt");

        System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + pizzeria.printAllIngredients());

        pizzeria.setCurrentComanda(new Comanda());
        pizzeria.setClientToComanda(new Client("Paperino", "bho", "0923 432665", new Address("Milano", "vialeZara", "33/b")));
        pizzeria.addPizza("margherita");
        pizzeria.confirmComanda();
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());

        pizzeria.addIngredientToPizza("acciughe", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());

        pizzeria.addIngredientToPizza("origano", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());

        pizzeria.addIngredientToPizza("capperi", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
         //System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());

        pizzeria.addIngredientToPizza("carciofi", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());

        pizzeria.addPizza("margherita");
        pizzeria.addIngredientToPizza("prosciutto cotto", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
        pizzeria.addIngredientToPizza("funghi", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
        pizzeria.addIngredientToPizza("salsiccia", 1);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());

        pizzeria.removePizza(0);
         System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
         
         pizzeria.addPizza("margherita");
      pizzeria.addIngredientToPizza("patatine", 1);
       System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
      
       pizzeria.addPizza("margherita");
       pizzeria.addIngredientToPizza("mozzarella", 2);
         System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
    }
}
