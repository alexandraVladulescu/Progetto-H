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
import data.CurrentComandaManagerModality;
import data.Ingredient;
import data.IngredientsManager;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Francesco
 */
public class SecondoCasoDusoTest {

    public static void main(String[] args) throws IOException, ComandaNotFoundException, PizzaNotFoundInMenuException, ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {

        System.out.println("ISTANZIO PIZZERIA E CARICO I MENU PRESENTI \n");
        Pizzeria pizzeria = new Pizzeria();

        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("\t MENU \t" + pizzeria.printMenuPizze() + "\t \n");

        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

        System.out.println("\tTUTTI GLI INGREDIENTI AGGIUNTI NEL DB:\n");
        System.out.println(pizzeria.printAllIngredients());

        CurrentComandaManager c = pizzeria.getCurrentComandaManager();

        System.out.println(" IL CLIENTE CHIAMA \n");
        System.out.println("SETTO COMANDA CORRENTE VUOTA \n");
        c.createComanda();

        System.out.println("ISTANZIO UN CLIENTE (SENZA AGGIUNGERLO A NESSUNA LISTA_CLIENT)\n");
        Client client0 = new Client("Mario", "Rossi", "3335568544", new Address("Milano", "Corso Como ", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        c.setClientToComanda(client0);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            c.addPizza("Margherita");
            c.addPizza("Capricciosa");
            c.addPizza("margherita");
            c.addPizza("chips");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
        c.setDeliveryTime(new GregorianCalendar(2016, Calendar.NOVEMBER, Calendar.FRIDAY, 12, 30));
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

        System.out.println("SE E' TUTTO OKAY CONFERMO LA COMANDA \n");
        c.confirmComanda();// AGGIUNGE ALLA LISTA LA COMANDA

        System.out.println("\t ESTENSIONE CASO D 'USO : IL CLIENTE RICHIAMA E VUOLE MODIFICARE IL SUO ORDINE\n");
        System.out.println("\t NOTA : NEL FRATTEMPO LA PIZZERIA POTREBBE AVER PRESO ALTRI n ORDINI\n");
        System.out.println("\t \t CHIAMA UN ALTRO CLIENTE \t \t \n");

        Client client1 = new Client("Claudio", "Cusano", "3465568874", new Address("Pavia", "Via Ferrata", "1"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        c.setClientToComanda(client1);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");

            c.addPizza("Capricciosa");
            c.addPizza("Marinara");
            c.addPizza("crudo");
            c.addPizza("crudo");
            c.addPizza("crudo");
            c.addPizza("trevisana");
        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
         c.setDeliveryTime(new GregorianCalendar(2016, Calendar.NOVEMBER, Calendar.THURSDAY, 19, 30));
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());
        c.confirmComanda();

        System.out.println("\t \t CHIAMA CLIENT0(Mario Rossi) DI PRIMA CHE VUOLE TOGLIERE LA MARGHERITA E AGGIUNGERE UNA CAPRICCIOSA \t \t \n");
        c.setModality(CurrentComandaManagerModality.MODIFY);
        c.setCurrentComanda(c.getComandaById(0));
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

        c.addPizza("Capricciosa"); //Ho gia settato il client0 current dando il nome Rossi

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());
        System.out.println("FIN QUI NESSUNA MODIFICA AGLI INGREDIENTI DI UNA PIZZA ORDINATA\n");
        c.confirmComanda();
        
        
        System.out.println("STAMPO TUTTE LE COMANDE CHE HO\n");
        System.out.println(c.printAllComande());

        System.out.println("Richiama Claudio e vuole modificare la capricciosa aggiungendo patatine e una margherita \n ");
        c.setModality(CurrentComandaManagerModality.MODIFY);
        c.setCurrentComanda(c.getComandaById(1));
        //System.out.println("Per tagliar la testa al toro setto currentCLient e stampo la sua comanda\n");
        // c.setCurrentComanda("Cusano");
       // System.out.println(c.getCurrentComanda());

        System.out.println("\t\t\t AGGIUNGO INGREDIENTE PATATINE \n");

        c.addIngredientToPizza("patatine", 0);
        System.out.println("\t\t\t AGGIUNGO MARGHERITA \n");
        c.addPizza("margherita");
        System.out.println("\t\t\tSTAMPO LA COMANDA \n");
        System.out.println(c.showComandaDetails());
        c.addIngredientToPizza("acciughe", 0);// PERCHè è DIVENTATA LA PRIMA NELLA LISTA
        c.addIngredientToPizza("salsiccia", c.getCurrentComanda().getPizzasList().size() - 1);//PERCHè AD OGNI MODIFICA LA RIAGGIUNGO PER ULTIMA
        c.addIngredientToPizza("prosciutto cotto",c.getCurrentComanda().getPizzasList().size()-2 );
        c.addIngredientToPizza("brie", c.getCurrentComanda().getPizzasList().size() - 1);
        c.addIngredientToPizza("mais", c.getCurrentComanda().getPizzasList().size() - 1);
         c.addIngredientToPizza("brie",c.getCurrentComanda().getPizzasList().size()-2 );
        System.out.println("\t\t\tSTAMPO LA COMANDA \n");

        System.out.println(c.showComandaDetails());
        c.confirmComanda();
        
        
        System.out.println("**************\t \t TUTTI GLI OERDINI \t \t *****************");
        System.out.println(c.printAllComande());
    }
}
