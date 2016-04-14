/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import i_o.MenuPizzeReader;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MenuPizze {

    private MenuPizzeReader menuReader;
    private ArrayList<Pizza> pizze;

    public MenuPizze() {

        menuReader = new MenuPizzeReader();
        pizze = new ArrayList<Pizza>();
    }

    public void loadMenu() {
        pizze = menuReader.readFile();
    }

    public void printAllPizzas() {
        for (Pizza pizza : pizze) {
            System.out.println(pizza.toString());
        }
    }

    public Pizza getPizzaByName(String name) {
        for (Pizza pizza : pizze) {
            if (pizza.getName().equals(name)) {
                return pizza;
            }
        }
        System.err.println("No such pizza in the menù");
        return null;
    }

}
