/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaTestV;

import data.Address;
import data.Client;
import data.Pizza;
import data.Pizzeria;

/**
 *
 * @author Francesco
 */
public class PizzaTest0 {

    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria("L'ORO DI NAPOLI");
        System.out.println("CREO OGGETTI PIZZA\n");

        Pizza margherita = new Pizza("Margherita", 4.5);
        Pizza quattroFormaggi = new Pizza("4Formaggi", 6);
        Pizza marinara = new Pizza("Marinara", 3.5);
        Pizza capricciosa = new Pizza("Capricciosa", 5.5);

        System.out.println("AGGIUNGO LE PIZZE ALLA LISTA DI PRODOTTI IN PIZZERIA\n");
        pizzeria.addProduct(margherita);
        pizzeria.addProduct(quattroFormaggi);
        pizzeria.addProduct(marinara);
        pizzeria.addProduct(capricciosa);
        

        System.out.println("CREO CLIENTI A CASO\n");
        Client client0 = new Client("Francesco", "Bruno",new Address("LODI", "Viale zara","33/B")) ;
        Client client1 = new Client("Giacomo", "Corradini", new Address("ROMA", "via della repubblica", "55"));
        Client client2 = new Client("Marco", "Robutti", new Address("DOMODOSSOLA", "via gluck", "72"));
        Client client3 = new Client("Davide","Albertini",new Address("Milano", "via JiMmYPage", "3"));
        Client client4 = new Client("Alexandra Nicole","Vladulescu",new Address("Pavia","n.d" , "n.d"));
        pizzeria.addClient(client0); 
         pizzeria.addClient(client1);
          pizzeria.addClient(client2);
          pizzeria.addClient(client3);
          pizzeria.addClient(client4);
          
          System.out.println("SUPPONGO CHIAMINO CLIENTI GIA' ISTANZIATI \n");
          System.out.println("SIMULO LA CHIAMATA DEL CLIENT 0\n");
          
        pizzeria.createComanda(); //creo comanda e automaticamente currentcomanda = comanda
        pizzeria.setHolder(client0); //Intesto alla comanda corrente il cliente
        pizzeria.addOrder(margherita);
        pizzeria.addOrder(margherita);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(marinara);
        pizzeria.addOrder(marinara);
        
       // System.out.println(pizzeria.getCurrentComanda().toString());
        
        pizzeria.createComanda(); //creo comanda e automaticamente currentcomanda = comanda
        pizzeria.setHolder(client1); //Intesto alla comanda corrente il cliente
        pizzeria.addOrder(margherita);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(marinara);
        
         pizzeria.createComanda(); //creo comanda e automaticamente currentcomanda = comanda
        pizzeria.setHolder(client2); //Intesto alla comanda corrente il cliente
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(capricciosa);
        pizzeria.addOrder(marinara);
        
        System.out.println("TUTTE LE COMANDE \n");
        System.out.println(pizzeria.getComandeList());

    }
}
