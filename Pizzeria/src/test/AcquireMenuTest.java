/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.MenuPizze;
import data.Pizzeria;
import i_o.AcquireTxtMenu;
import i_o.AcquireXmlMenu;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class AcquireMenuTest {

    // ./databases/pizze.txt
    // ./databases/MenuPizze.xml

    public static void main(String[] args) throws IOException {
        System.out.println("Test istanziando un menuPizze dal Main \n");
        MenuPizze menuPizze = new MenuPizze();
        menuPizze.loadMenu(new AcquireTxtMenu());
        System.out.println(menuPizze.printAllPizzas());

        menuPizze.loadMenu(new AcquireXmlMenu());
        System.out.println(menuPizze.printAllPizzas());

        System.out.println("Test istanziando Pizzeria\n");
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadMenues();
        System.out.println(pizzeria.getMenuPizze());

    }
}
