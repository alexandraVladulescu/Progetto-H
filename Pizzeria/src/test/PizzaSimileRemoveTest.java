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
import i_o_V1.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class PizzaSimileRemoveTest {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException, ProductNotFoundException, IngredientNotFoundException {
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt");

        System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + pizzeria.printAllIngredients());

        pizzeria.setCurrentComanda(new Comanda());
        pizzeria.setClientToComanda(new Client("Paperino", "bho", "0923 432665", new Address("Milano", "vialeZara", "33/b")));
        
        pizzeria.addPizza("napoli");
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
        
        pizzeria.removeIngredientToPizza("origano", 0);
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
        pizzeria.removeIngredientToPizza("capperi", 0);
        
          pizzeria.removeIngredientToPizza("acciughe", 0);
          pizzeria.removeIngredientToPizza("capperi", 0);
          System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
         
          pizzeria.removeIngredientToPizza("mozzarella", 0);
           System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
         
         
    }
    
}
