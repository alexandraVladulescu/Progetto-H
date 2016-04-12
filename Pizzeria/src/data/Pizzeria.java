/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public class Pizzeria {

    ArrayList<Product> productList;
    ArrayList<Client> clientList;
    ArrayList<Comanda> comandaList;
    String name;
    //Client currentClient;
    Comanda currentComanda;

    public Pizzeria(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
        clientList = new ArrayList<Client>();
        comandaList = new ArrayList<Comanda>();

    }

    public void setHolder(Client c) {
        this.currentComanda.setClient(c);
    }

    public void addProduct(Product p) {
        this.productList.add(p);
    }

    public void addClient(Client c) {
        // currentClient = c;
        this.clientList.add(c);
    }

    public void createComanda() {
        this.currentComanda = new Comanda();
        addComanda(currentComanda);
    }

    public void addComanda(Comanda c) {
        this.comandaList.add(c);
    }

    public Comanda getCurrentComanda() {
        return currentComanda;
    }

    public void setCurrentComanda(Comanda currentComanda) {
        this.currentComanda = currentComanda;
    }

    public void addOrder(Product p) {
        this.currentComanda.addOrder(p);
    }

    public String getComandeList() {
        String s = "";
        for (Comanda comandaList1 : comandaList) {
            s += comandaList1.toString() + "\n";
        }
        return s;
    }
}
