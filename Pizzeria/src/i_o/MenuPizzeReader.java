/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o;

import data.Pizza;
import java.util.ArrayList;
import org.w3c.dom.*;
/**
 *This Class reads the xml menù file containing all the avaliable pizzas and stores them
 * in an ArrayList
 * @author User
 */
public class MenuPizzeReader{
    
    private Document xmlMenu;
    
    public ArrayList<Pizza> readFile(){
        
        ArrayList<Pizza> pizze = new ArrayList<Pizza>();
        
        xmlMenu = MyXmlParser.getDocument("./databases/MenuPizze.xml");
        
        NodeList pizzaList = xmlMenu.getElementsByTagName("pizza");
        
        for (int i = 0; i < pizzaList.getLength(); i++) {
            
            Node pizza  = pizzaList.item(i);
            //Livello <pizza><all children>
            NodeList attrpizza = pizza.getChildNodes();
            
            String name = attrpizza.item(0).getFirstChild().getNodeValue().trim();
            
            String price = attrpizza.item(1).getFirstChild().getNodeValue().trim();
            
           pizze.add(new Pizza(name, Double.valueOf(price)));
           
           //TODO: gestione ingredienti
        }
        return pizze;
    }
    
  
}
