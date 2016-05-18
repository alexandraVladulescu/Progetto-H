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
import data.Pizza;
import exceptions.PizzaNotFoundInMenuException;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author User
 */
public class TestPrimoCasoDUso {

    public static void main(String[] args) throws IOException, CloneNotSupportedException, ComandaNotFoundException {
        
        
        System.out.println("ISTANZIO PIZZERIA E CARICO I MENU PRESENTI \n");
        Pizzeria p = new Pizzeria();
        p.loadMenuPizza("./databases/MenuPizze.xml", FormatType.XML);
        CurrentComandaManager c = p.getCurrentComandaManager();
        System.out.println(" IL CLIENTE CHIAMA \n");
        System.out.println("SETTO COMANDA CORRENTE VUOTA \n");
        c.setCurrentComanda(new Comanda());

        System.out.println("ISTANZIO UN CLIENTE (SENZA AGGIUNGERLO A NESSUNA LISTA_CLIENT)\n");
        Client client = new Client("Mario", "Rossi","3335568544", new Address("Lll", "ooo", "528"));
        
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
        c.confirmComanda();

    }

}
