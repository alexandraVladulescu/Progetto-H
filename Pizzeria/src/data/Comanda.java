/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Francesco
 */
public class Comanda {

    private ArrayList<Pizza> pizzasList = new ArrayList<Pizza>();
    private Client client;
    private Calendar deliveryTime = new GregorianCalendar();
    private int id = 0;
    //Quando la comanda viene evasa lo setto a true da interfaccia ;
    //Rimane false finchè le pizze non escono dalla pizzeria !
    private boolean terminated = false;

    public Comanda() {
        //Devo impostarlo a null sennò come valore di default ha la data attuale...
        this.deliveryTime = null;
    }

    public void addPizza(Pizza p) {
        this.pizzasList.add(p);
    }

    @Override
    public String toString() {
        return "CLIENT >> " + this.client.toString() + "\t"
                + "\n\nOra di Consegna " + getTime() + "\n"
                + "\nOrdini : \n" + this.getOrderedPizzas() + "\n "
                + "\nTotal : " + this.calculateTotalPrice()+ "\n "
                +"\nID : " + this.getId();
    }

    public String getOrderedPizzas() {
        int index = 0;
        String s = "";
        for (Pizza p : pizzasList) {
            index++;
            s += index + ".\t" + p.toString() + "\n";
        }
        return s;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Pizza p : pizzasList) {
            totalPrice += p.getPrice();

        }
        return totalPrice;
    }

    public Calendar getDeliveryTime() {
        return deliveryTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDeliveryTime(Calendar data) {
        this.deliveryTime = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void removePizza(int index) throws ProductNotFoundException {
        Pizza pizzaTrovata = this.searchPizzaByIndex(index);
        this.pizzasList.remove(pizzaTrovata);
    }

//    public Pizza searchPizzaByName(String namePizza) throws ProductNotFoundException {
//        Pizza p = null;
//        for (Pizza ord : pizzasList) {
//            if (ord.getName().equalsIgnoreCase(namePizza)) {
//                p = ord;
//                break; //fa schifo
//
//            }
//        }
//        if (p == null) {
//            throw new ProductNotFoundException("\t NESSUN PRODOTTO TROVATO INERENTE A : \t" + namePizza);
//        }
//        return p;
//    }

    public Pizza searchPizzaByIndex(int index) throws ProductNotFoundException {
        Pizza p = null;
        if (index < pizzasList.size()) {
            p = pizzasList.get(index);
        } else {
            throw new ProductNotFoundException("\t PRODUCT NOT FOUND FOR INDEX -->" + index);
        }

        return p;
    }

    private String getTime() {
        return deliveryTime.getTime().toString();
    }

    public ArrayList<Pizza> getPizzasList() {
        return pizzasList;
    }

    public void terminate() {
        terminated = true;
    }

    public boolean getTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //Creiamo una nuova comanda e settiamola uguale a this in tutti i suoi campi
        Comanda comanda = new Comanda();
        for (Pizza pizza : pizzasList) {
            comanda.addPizza((Pizza) pizza.clone());
        }
        int year = this.deliveryTime.get(Calendar.YEAR);
        int month = this.deliveryTime.get(Calendar.MONTH);
        int day = this.deliveryTime.get(Calendar.DAY_OF_MONTH);
        int hour = this.deliveryTime.get(Calendar.HOUR_OF_DAY);
        int minuts = this.deliveryTime.get(Calendar.MINUTE);
        comanda.setId(this.getId());
        comanda.deliveryTime = new GregorianCalendar(year, month, day, hour, minuts);
        comanda.terminated = this.terminated;
        comanda.setClient((Client) this.client.clone());
        return comanda;

    }

}
