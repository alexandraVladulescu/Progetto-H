/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.MenuPizze;
import data.Pizzeria;

import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class LoaderMenuTest {

    // ./databases/pizze.txt
    // ./databases/MenuPizze.xml

    public static void main(String[] args) throws IOException {
        System.out.println("Test istanziando un menuPizze dal Main \n");
        MenuPizze menuPizze = new MenuPizze();
        menuPizze.loadMenu("./databases/MenuPizze.xml", FormatType.XML);
        System.out.println(menuPizze.printAllPizzas());

        menuPizze.loadMenu("./databases/pizze.txt", FormatType.TXT);
        System.out.println(menuPizze.printAllPizzas());

       

    }
}
