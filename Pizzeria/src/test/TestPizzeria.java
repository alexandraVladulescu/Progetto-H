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
import exceptions.PizzaNotFoundInMenuException;
import data.Pizzeria;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class TestPizzeria {

    // ./databases/pizze.txt
    // ./databases/MenuPizze.xml
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Pizzeria pizzeria= new Pizzeria();
        CurrentComandaManager c = pizzeria.getCurrentComandaManager();
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        c.setCurrentComanda(new Comanda());
        Client client = new Client("Mario", "Rossi", "3335568544", new Address("Lll", "ooo", "528"));
        c.setClientToComanda(client);

        try {
            System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            c.addPizza("Margherita");
            c.addPizza("Capricciosa");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }

    }
}
