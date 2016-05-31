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
import java.util.Collections;

/**
 *
 * @author Francesco
 */
public class MenuCalzoni {

    private ArrayList<DescriptionPizza> calzoni;
    private static MenuCalzoni menuCalzoni;

    private MenuCalzoni() {

    }

    public static MenuCalzoni getInstance() {
        if (menuCalzoni == null) {
            menuCalzoni = new MenuCalzoni();
        }
        return menuCalzoni;
    }

    public void loadMenu(String path, FormatType type) throws IOException { //MI ARRIVANO COME PRODUCT, FACCIO IL CAST
        //E' necessario fare il clear della collection altrimenti
        //due caricamenti consecutivi possono sdoppiare le calzoni
        calzoni.clear();
        MenuPizzaLoader menuPizzaLoader = new MenuPizzaLoader();
        PizzaReaderFactory reader = menuPizzaLoader.getFilePizzaReader(path, type);
        while (reader.hasNextProduct()) {
            calzoni.add(reader.getNextProduct());
        }
        Collections.sort(calzoni);

    }

    public ArrayList<DescriptionPizza> getCalzoni() {
        return this.calzoni;
    }

    public int getMenuSize() {
        return calzoni.size();
    }
     public DescriptionPizza getCalzoneByName(String name) throws PizzaNotFoundInMenuException {
        DescriptionPizza p = null;
        for (DescriptionPizza pizza : calzoni) {
            if (pizza.getName().equalsIgnoreCase(name)) {
                //return pizza;
                p = pizza;
            }
        }
        if (p == null) {
            throw new PizzaNotFoundInMenuException("PIZZA NOT FOUND EXCEPTION ; CALZONE\t" + name + "\n");
        }
        return p;
    }

}
