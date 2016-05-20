/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.CurrentComandaManager;
import data.CurrentComandaManagerModality;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Francesco
 */
public class NuovoAlgoritmoIngredientTest {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException, ComandaNotFoundException, ProductNotFoundException, IngredientNotFoundException {
        System.out.println("ISTANZIO PIZZERIA E CARICO I MENU PRESENTI \n");
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("\t MENU \t" + pizzeria.printMenuPizze() + "\t \n");

        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

        System.out.println("\tTUTTI GLI INGREDIENTI AGGIUNTI NEL DB:\n");
        System.out.println(pizzeria.printAllIngredients());

        CurrentComandaManager c = pizzeria.getCurrentComandaManager();

        c.createComanda();
        c.addPizza("prosciutto");
        c.addPizza("funghi");

        System.out.println("ISTANZIO UN CLIENTE (SENZA AGGIUNGERLO A NESSUNA LISTA_CLIENT)\n");
        Client client0 = new Client("Mario", "Rossi", "3335568544", new Address("Milano", "Corso Como ", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        c.setClientToComanda(client0);

        c.setDeliveryTime(new GregorianCalendar(2016, Calendar.NOVEMBER, Calendar.FRIDAY, 12, 30));
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

        c.confirmComanda();

        c.setModality(CurrentComandaManagerModality.MODIFY);
        c.setCurrentComanda(c.getComandaById(0));

        c.addIngredientToPizza("prosciutto cotto", 1);
        c.addIngredientToPizza("funghi", 0);
        c.addIngredientToPizza("acciughe", 0);
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());
        c.confirmComanda();
        
        c.setModality(CurrentComandaManagerModality.MODIFY);
        c.setCurrentComanda(c.getComandaById(0));
        c.removeIngredientToPizza("prosciutto cotto", 0);
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());
        c.confirmComanda();
        
        
    }
}
