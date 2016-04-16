/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o;


import data.Pizza;
import data.Product;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.*;

/**
 * This Class reads the xml menù file containing all the avaliable pizzas and
 * stores them in an ArrayList
 *
 * @author User
 */
public class PizzeXmlReader implements FileProductReader {

    private Document xmlMenu;
    private NodeList pizzaList;
    private int indexList;
//./databases/MenuPizze.xml
    public PizzeXmlReader(String path) {

        xmlMenu = MyXmlParser.getDocument(path);
         this.pizzaList = xmlMenu.getElementsByTagName("pizza");// HO LA STRUTTURA NODELIST
        indexList = 0;
    }

    @Override
    public Product getNextProduct() throws IOException {//RICORDARE CHE QUESTO VIENE RICHIAMATO PER SECONDO
        Node pizza = pizzaList.item(indexList);
        //Livello <pizza><all children>
        NodeList attrpizza = pizza.getChildNodes();

        String name = attrpizza.item(0).getFirstChild().getNodeValue().trim();

        String price = attrpizza.item(1).getFirstChild().getNodeValue().trim();

        indexList++;
        return new Pizza(name, Double.valueOf(price));
    }

    @Override
    public boolean hasNextProduct() throws IOException {//RICORDARE CHE VIENE RICHIAMATO PER PRIMO
        if (this.indexList < pizzaList.getLength()) {

            return true;
        } else {
            return false;
        }
    }
                            
//    public ArrayList<Pizza> readFile() {
//
//        ArrayList<Pizza> pizze = new ArrayList<Pizza>();
//
//        xmlMenu = MyXmlParser.getDocument("./databases/MenuPizze.xml");
//
//        NodeList pizzaList = xmlMenu.getElementsByTagName("pizza");
//
//        for (int i = 0; i < pizzaList.getLength(); i++) {
//
//            Node pizza = pizzaList.item(i);
//            //Livello <pizza><all children>
//            NodeList attrpizza = pizza.getChildNodes();
//
//            String name = attrpizza.item(0).getFirstChild().getNodeValue().trim();
//
//            String price = attrpizza.item(1).getFirstChild().getNodeValue().trim();
//
//            pizze.add(new Pizza(name, Double.valueOf(price)));
//
//            //TODO: gestione ingredienti
//        }
//        return pizze;
//    }

}
