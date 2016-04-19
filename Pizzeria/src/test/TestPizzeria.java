/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.Comanda;
import exceptions.PizzaNotFoundInMenuException;
import data.Pizzeria;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class TestPizzeria {
    public static void main(String[] args) throws IOException {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadMenues();
        pizzeria.setCurrentComanda(new Comanda());
        Client client = new Client("Mario", "Rossi", new Address("Lll", "ooo", "528"));
        pizzeria.setClientToComanda(client);
        
         try {
                System.out.println("AGGIUNGO PRODOTTI ALLA COMANDA CORRENTE \n");
            pizzeria.addPizza("Margherita");
            pizzeria.addPizza("Capricciosa");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
