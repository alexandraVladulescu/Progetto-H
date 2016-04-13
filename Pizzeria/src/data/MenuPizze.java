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
    
    private void fillMenu(){
        //pizze = menuReader.readFile();
    } 
    
}
