/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.PizzaNotFoundInMenuException;
import i_o.FormatType;
import i_o.input.pizza_reader.MenuPizzaLoader;
import i_o.input.pizza_reader.PizzaReaderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author User
 */
public class MenuPizze implements Cloneable {

    //AcquireMenu acquireMenu;
    // private MenuPizzeReader menuReader;
    private ArrayList<Pizza> pizze;

    public MenuPizze() {

        pizze = new ArrayList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MenuPizze mP = new MenuPizze();
        for (Pizza p : pizze) {
            mP.pizze.add((Pizza) p.clone());
        }
        return mP;

    }
// ./databases/pizze.txt
    //./databases/MenuPizze.xml

    public void loadMenu(String path, FormatType type) throws IOException { //MI ARRIVANO COME PRODUCT, FACCIO IL CAST
        MenuPizzaLoader menuPizzaLoader = new MenuPizzaLoader();
        PizzaReaderFactory reader = menuPizzaLoader.getFilePizzaReader(path, type);
        while (reader.hasNextProduct()) {
            pizze.add(reader.getNextProduct());
        }
        Collections.sort(pizze);

    }

    public ArrayList<Pizza> printAllPizzas() {
        return pizze;
    }

    public Pizza getPizzaByName(String name) throws PizzaNotFoundInMenuException {
        Pizza p = null;
        for (Pizza pizza : pizze) {
            if (pizza.getName().equalsIgnoreCase(name)) {
                //return pizza;
                p = pizza;
            }
        }
        if (p == null) {
            throw new PizzaNotFoundInMenuException("PIZZA NOT FOUND EXCEPTION ; PIZZA\t" + name + "\n");
        }
        return p;
    }

    public int getMenuSize() {
        return pizze.size();
    }

    public ArrayList<Pizza> getPizze() {
        return pizze;
    }

}
