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
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class margheritaTest {
    public static void main(String[] args) throws PizzaNotFoundInMenuException, ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException, IOException {
         Pizzeria p = new Pizzeria();
CurrentComandaManager c = p.getCurrentComandaManager();
        p.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        p.loadIngredientsMenu("./databases/ingredienti.txt",FormatType.TXT);

        System.out.println("\t\t\t\t MENU PIZZE\n" + p.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + p.printAllIngredients());

        c.setCurrentComanda(new Comanda());
        c.setClientToComanda(new Client("Paperino", "bho", "0923 432665", new Address("Milano", "vialeZara", "33/b")));

        c.addPizza("margherita");
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
        
        c.addPizza("margherita");
          System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
          
           c.addPizza("margherita");
        System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
        
        c.addPizza("margherita");
          System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
          
          
          c.addIngredientToPizza("olive", 2);
          System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
    
//          c.addIngredientToPizza("olive nere", 2);
          
          System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
          c.addIngredientToPizza("olive nere", 3);
          
          System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
          
          c.addIngredientToPizza("doppia mozzarella", 0);
               System.out.println("\t\t\t\t\tDETTAGLI\n" + c.showComandaDetails());
    }
       
        
    
}
