/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.Pizzeria;
import i_o_V1.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class LoadPizzaWithIngredientTests {
    public static void main(String[] args) throws IOException {
        Pizzeria p = new Pizzeria();
        p.loadMenuPizza("./databases/MenuPizze.xml", FormatType.XML);
        System.out.println(p.printMenuPizze());
    }
}
