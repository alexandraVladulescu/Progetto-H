/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.ComandeManager;
import data.CurrentComandaManager;
import data.CurrentComandaManagerModality;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Francesco
 */
public class SecondoCasoDuso2Test {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException, ComandaNotFoundException, ProductNotFoundException, IngredientNotFoundException {
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);

        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

        System.out.println("\t\t\t\t MENU PIZZE\n" + pizzeria.printMenuPizze());
        System.out.println("\t\t\t\t INGREDIENTI PRESENTI\n" + pizzeria.printAllIngredients());

        CurrentComandaManager c = pizzeria.getCurrentComandaManager();

        c.createComanda();//CREO UNA NUOVA COMANDA CHE VIENE SETTATA COME CURRENT

        c.addPizza("margherita");
        c.addPizza("capricciosa");
        c.addPizza("chips");

        c.setClientToComanda(new Client("Francesco", "Brown", "999784738", new Address("Lll", "ooo", "528")));
        c.setDeliveryTime(new GregorianCalendar(Locale.ENGLISH));

        System.out.println("\t\tDETAILS\n" + c.showComandaDetails());
        c.confirmComanda();

        // c.createComanda();
        c.addPizza("chips");
        c.addPizza("trevisana");
        c.setClientToComanda(new Client("Francesco", "BBking", "00084738", new Address("ll", "opo", "528")));
        c.setDeliveryTime(new GregorianCalendar(Locale.ENGLISH));

        System.out.println("\t\tDETAILS\n" + c.showComandaDetails());

        c.confirmComanda();

        System.out.println("\t\t\t\t\t\t TUTTE LE COMANDE\n" + c.printAllComande());
//
        c.setModality(CurrentComandaManagerModality.MODIFY);
//        
        c.setCurrentComanda(c.getComandaById(1));
        System.out.println("\t\tDETAILS\n" + c.showComandaDetails());
//         
//         
//       
        c.addPizza("margherita");
        c.addIngredientToPizza("salsiccia", 0);
        c.confirmComanda();

////        
        System.out.println("\t\t\t\t\t\t TUTTE LE COMANDE\n" + c.printAllComande());
        System.out.println(c.getComandeBySurname("BBking").toString());

    }
}
