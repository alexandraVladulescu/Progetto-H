/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.MenuPizze;
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
      MenuPizze menuPizze = new MenuPizze();
      menuPizze.loadMenu(new AcquireTxtMenu(),"./databases/pizze.txt");
        System.out.println(menuPizze.printAllPizzas());
        
          menuPizze.loadMenu(new AcquireXmlMenu(),"./databases/MenuPizze.xml");
        System.out.println(menuPizze.printAllPizzas());
        
        
        
        
        
    }
}
