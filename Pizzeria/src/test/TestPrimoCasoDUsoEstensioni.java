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
import exceptions.ComandaNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import data.Pizzeria;
import exceptions.ProductNotFoundException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class TestPrimoCasoDUsoEstensioni {

    public static void main(String[] args) throws IOException, ComandaNotFoundException, ProductNotFoundException, PizzaNotFoundInMenuException, CloneNotSupportedException {

        System.out.println("ISTANZIO PIZZERIA E CARICO I MENU PRESENTI \n");
        Pizzeria p = new Pizzeria();
        CurrentComandaManager c = p.getCurrentComandaManager();
        p.loadMenuPizza("./databases/MenuPizze.xml", FormatType.XML);
        System.out.println("\t MENU \t" + p.printMenuPizze() + "\t \n");

        System.out.println(" IL CLIENTE CHIAMA \n");
        System.out.println("SETTO COMANDA CORRENTE VUOTA \n");
        c.setCurrentComanda(new Comanda());

        System.out.println("ISTANZIO UN CLIENTE (SENZA AGGIUNGERLO A NESSUNA LISTA_CLIENT)\n");
        Client client = new Client("Mario", "Rossi", "3335568544", new Address("Lll", "ooo", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        c.setClientToComanda(client);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            c.addPizza("Margherita");
            c.addPizza("Capricciosa");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

        System.out.println("SE E' TUTTO OKAY CONFERMO LA COMANDA \n");
        c.confirmComanda();// AGGIUNGE ALLA LISTA LA COMANDA

        System.out.println("\t ESTENSIONE CASO D 'USO : IL CLIENTE RICHIAMA E VUOLE MODIFICARE IL SUO ORDINE\n");
        System.out.println("\t NOTA : NEL FRATTEMPO LA PIZZERIA POTREBBE AVER PRESO ALTRI n ORDINI\n");
        System.out.println("\t \t CHIAMA UN ALTRO CLIENTE \t \t \n");
        c.setCurrentComanda(new Comanda());

        Client client1 = new Client("Claudio", "Cusano", "3465568874", new Address("Lll", "ooo", "528"));

        System.out.println("INTESTO LA COMANDA AL CLIENTE \n");
        c.setClientToComanda(client1);
        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            c.addPizza("Marinara");
            c.addPizza("Capricciosa");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());
        c.confirmComanda();
        System.out.println("\t \t CHIAMA IL CLIENTE DI PRIMA CHE VUOLE TOGLIERE LA MARGHERITA E AGGIUNGERE UNA CAPRICCIOSA \t \t \n");
        c.setCurrentComanda("Rossi");

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

        c.addPizza("Capricciosa");

        System.out.println("RIASSUNTO COMANDA \n");
        System.out.println(c.showComandaDetails());

    }
}
