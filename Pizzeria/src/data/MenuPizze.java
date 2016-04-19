/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import i_o.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MenuPizze {

    //AcquireMenu acquireMenu;
    
    // private MenuPizzeReader menuReader;
    private ArrayList<Pizza> pizze;

    public MenuPizze() {
        
        //acquireMenu = new AcquireXmlMenu();
        //   menuReader = new MenuPizzeReader();
        pizze = new ArrayList<>();
    }
// ./databases/pizze.txt
    //./databases/MenuPizze.xml
    public void loadMenu(MenuLoader loader) throws IOException { //MI ARRIVANO COME PRODUCT, FACCIO IL CAST
        loader.fillProductList("pizza");
        ArrayList<Product> temp = new ArrayList<>();
        temp = loader.getMenu();
        for (Product temp1 : temp) {
            pizze.add((Pizza) temp1);

        }
    }

    public ArrayList<Pizza> printAllPizzas() {
        return pizze;
    }

    public Pizza getPizzaByName(String name) throws PizzaNotFoundInMenuException {
        Pizza p = null;
        for (Pizza pizza : pizze) {
            if (pizza.getName().equals(name)) {
                //return pizza;
                p = pizza;
            }
        }
        if (p == null) {
            throw new PizzaNotFoundInMenuException("PIZZA NOT FOUND EXCEPTION");
        }
        return p;
    }
    
    public int getMenuSize(){
        return pizze.size();
    }

    public ArrayList<Pizza> getPizze() {
        return pizze;
    }

}
