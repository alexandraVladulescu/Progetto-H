/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.Comanda;
import data.Ingredient;
import data.IngredientsManager;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import i_o_V1.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class SecondoCasoDusoTest {

    public static void main(String[] args) throws IOException, ComandaNotFoundException, PizzaNotFoundInMenuException, ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {

        System.out.println("ISTANZIO PIZZERIA E CARICO I MENU PRESENTI \n");
        Pizzeria p = new Pizzeria();
        p.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        System.out.println("\t MENU \t" + p.printMenuPizze() + "\t \n");

        System.out.println(" IL CLIENTE CHIAMA \n");
        System.out.println("SETTO COMANDA CORRENTE VUOTA \n");
        p.setCurrentComanda(new Comanda());

        System.out.println("ISTANZIO UN CLIENTE (SENZA AGGIUNGERLO A NESSUNA LISTA_CLIENT)\n");
        Client client = new Client("Mario", "Rossi", "3335568544", new Address("Lll", "ooo", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        p.setClientToComanda(client);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            p.addPizza("Margherita");
            p.addPizza("Capricciosa");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(p.showComandaDetails());

        System.out.println("SE E' TUTTO OKAY CONFERMO LA COMANDA \n");
        p.confirmComanda();// AGGIUNGE ALLA LISTA LA COMANDA

        System.out.println("\t ESTENSIONE CASO D 'USO : IL CLIENTE RICHIAMA E VUOLE MODIFICARE IL SUO ORDINE\n");
        System.out.println("\t NOTA : NEL FRATTEMPO LA PIZZERIA POTREBBE AVER PRESO ALTRI n ORDINI\n");
        System.out.println("\t \t CHIAMA UN ALTRO CLIENTE \t \t \n");
        p.setCurrentComanda(new Comanda());

        Client client1 = new Client("Claudio", "Cusano", "3465568874", new Address("Lll", "ooo", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        p.setClientToComanda(client1);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");

            p.addPizza("Capricciosa");
            p.addPizza("Marinara");
        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(p.showComandaDetails());
        p.confirmComanda();
        System.out.println("\t \t CHIAMA IL CLIENTE DI PRIMA CHE VUOLE TOGLIERE LA MARGHERITA E AGGIUNGERE UNA CAPRICCIOSA \t \t \n");
        p.setCurrentComanda("Rossi");
        System.out.println(p.showComandaDetails());
        p.removePizza("Margherita");

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(p.showComandaDetails());

        p.addPizza("Capricciosa"); //Ho gia settato il client current dando il nome Rossi

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(p.showComandaDetails());
        System.out.println("FIN QUI NESSUNA MODIFICA AGLI INGREDIENTI DI UNA PIZZA ORDINATA\n");

        System.out.println("CARICO DB INGREDIENTI DA MAIN\n");
        p.loadIngredientsMenu("./databases/ingredienti.txt");
//        IngredientsManager i = new IngredientsManager();
//        i.addIngredient(new Ingredient("acciuga", 0.5));
//        i.addIngredient(new Ingredient("wurstel", 1));
//        i.addIngredient(new Ingredient("patatine", 1.5));
//        i.addIngredient(new Ingredient("salmone", 2));

        System.out.println("\tTUTTI GLI INGREDIENTI AGGIUNTI NEL DB:\n");
        System.out.println(p.printAllIngredients());

        System.out.println("STAMPO TUTTE LE COMANDE CHE HO\n");
        System.out.println(p.printAllComande());

        System.out.println("Richiama Claudio e vuole modificare la capricciosa aggiungendo  ");
        System.out.println("Per tagliar la testa al toro setto currentCLient e stampo la sua comanda\n");
        p.setCurrentComanda("Cusano");
        System.out.println(p.getCurrentComanda());

        System.out.println("\t\t\t AGGIUNGO INGREDIENTE ACCIUGA \n");

        p.addIngredientToPizza("acciughe", 0);
        p.addPizza("margherita");
        System.out.println("\t\t\tSTAMPO LA COMANDA \n");
        
        p.addIngredientToPizza("acciughe", 1);
        p.addIngredientToPizza("origano", 1);
        System.out.println("\t\t\tSTAMPO LA COMANDA \n");

        System.out.println(p.showComandaDetails());

        System.out.println("\t MENU PIZZE \n");
        System.out.println(p.printMenuPizze());
    }
}