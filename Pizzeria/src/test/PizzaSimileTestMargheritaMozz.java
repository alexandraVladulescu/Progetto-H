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
public class PizzaSimileTestMargheritaMozz {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException, ProductNotFoundException, IngredientNotFoundException {
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt",FormatType.TXT);

        System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + pizzeria.printAllIngredients());

        pizzeria.setCurrentComanda(new Comanda());
        pizzeria.setClientToComanda(new Client("Paperino", "bho", "0923 432665", new Address("Milano", "vialeZara", "33/b")));

        pizzeria.addPizza("margherita");
        System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
        
        pizzeria.addIngredientToPizza("mozzarella", 0);
         System.out.println("\t\t\t\t\tDETTAGLI\n" + pizzeria.showComandaDetails());
         
        
    }
}
