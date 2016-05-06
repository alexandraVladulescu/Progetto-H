/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.CurrentComandaManager;
import data.Pizzeria;
import exceptions.PizzaNotFoundInMenuException;
import i_o.FormatType;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class TestCurrentComandaManager {

    public static void main(String[] args) throws IOException, PizzaNotFoundInMenuException, CloneNotSupportedException {
        Pizzeria pizzeria = new Pizzeria();
       // pizzeria.loadIngredientsMenu(null, FormatType.TXT);
        CurrentComandaManager c = pizzeria.getCurrentComandaManager();
        c.createComanda();
        c.addPizza("margherita");
        c.addPizza("capricciosa");
        
        
        
    }
}
