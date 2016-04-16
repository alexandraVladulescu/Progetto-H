/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.MenuPizze;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class AcquireMenuTest {
    
    public static void main(String[] args) throws IOException {
      MenuPizze menuPizze = new MenuPizze();
      menuPizze.loadMenu();
        System.out.println(menuPizze.printAllPizzas());
    }
}
