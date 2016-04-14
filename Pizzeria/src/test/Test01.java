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
import data.Pizzeria;

/**
 *
 * @author User
 */
public class Test01 {
    
    public static void main(String[] args) {
        
        Pizzeria p = new Pizzeria();
        Client client = new Client("Mario", "Rossi", new Address("Lll", "ooo", "528"));
        
        p.createComanda(new Comanda());
        p.addPizza("Margherita");
        p.addPizza("Capricciosa");
        //p.addPizza("Gozza");
        p.getCurrentComanda().setClient(client);
        System.out.println(p.getCurrentComanda().toString());
        
        
        
    }
    
}
