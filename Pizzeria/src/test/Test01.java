/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Address;
import data.Client;
import data.Comanda;
import data.Pizza;
import data.PizzaNotFoundInMenuException;
import data.Pizzeria;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        Pizzeria p = new Pizzeria();
        Client client = new Client("Mario", "Rossi", new Address("Lll", "ooo", "528"));

        p.createComanda(new Comanda());
        try {

            p.addPizza("Margherita");
            p.addPizza("Capricciosa");
            p.addPizza("Gozza");

        } catch (PizzaNotFoundInMenuException e) {
            System.out.println(e.getMessage());
        }
        p.getCurrentComanda().setClient(client);
        System.out.println(p.getCurrentComanda().toString());

    }

}
