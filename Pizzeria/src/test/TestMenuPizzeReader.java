
import data.Pizza;
import data.Product;
import i_o.MenuLoader;

import i_o.XmlMenuLoader;
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

   
    public static void main(String[] args) throws IOException  {
        XmlMenuLoader xmlMenuLoader = new XmlMenuLoader();
        ArrayList<Product> pizze = new ArrayList<>();
        
    
         
//        pizze = reader.readFile();
        xmlMenuLoader.fillProductList("pizza");
         pizze = xmlMenuLoader.getMenu();
        for (Product pizza : pizze) {
            System.out.println(pizza.toString());
        }
    }
}
