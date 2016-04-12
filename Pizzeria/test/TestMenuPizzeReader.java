
import data.Pizza;
import i_o.MenuPizzeReader;
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

   
    public static void main(String[] args) {
        
        ArrayList<Pizza> pizze = new ArrayList<Pizza>();
        
        MenuPizzeReader reader = new MenuPizzeReader();
         
        pizze = reader.readFile();
         
        for (Pizza pizza : pizze) {
            System.out.println(pizza.toString());
        }
    }
}
