package test;


import data.Pizza;
import data.Pizzeria;
import data.Product;
import i_o.MenuLoader;

import i_o.XmlMenuLoader;
import i_o_V1.FormatType;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class TestMenuPizzeReader {

    // ./databases/pizze.txt
    // ./databases/MenuPizze.xml
   
    public static void main(String[] args) throws IOException  {
     
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadMenuPizza("./databases/MenuPizze.xml", FormatType.XML);
        
        for (int i = 0; i < pizzeria.getMenuPizze().getPizze().size(); i++) {
            pizzeria.getMenuPizze().printAllPizzas();
        }
    }
}
