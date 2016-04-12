/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Francesco
 */
public class Comanda {

    private ArrayList<Product> ordersList = new ArrayList<Product>();
   // private double totalPrice;
    private Calendar deliveryTime = new GregorianCalendar();
//    private String name;
//    private String surname;
//    private String indirizzo;
    private Client client;

    public Comanda() {
          //  this.totalPrice = 0.0;
    }

    public void addOrder(Product p) {
        this.ordersList.add(p);
    }

    @Override
    public String toString() {
        return "CLIENT >> " + this.client.toString() + "" + "OraDiConsegna " + this.deliveryTime + "\n"
                + "Ordini : \n" + this.getOrderedProducts()+"\n "+
                "Total : "+this.calculateTotalPrice();
    }

    public String getOrderedProducts() {
        int index = 0;
        String s = "";
        for (Product ordersList1 : ordersList) {
            index++;
            s +=  index + ". " + ordersList1 + "\n";
        }
        return s;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product ordersList1 : ordersList) {
            totalPrice += ordersList1.getPrice();
            
        }
        return totalPrice;
    }

    public Calendar getDeliveryTime() {
        return deliveryTime;
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

}
