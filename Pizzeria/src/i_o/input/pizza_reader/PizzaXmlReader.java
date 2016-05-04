/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.pizza_reader;

import data.Ingredient;
import data.Pizza;
import data.Product;
import i_o.MyXmlParser;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.*;

/**
 * This Class reads the xml menù file containing all the avaliable pizzas and
 * stores them in an ArrayList
 *
 * @author User
 */
public class PizzaXmlReader implements PizzaReaderFactory {

    private Document xmlMenu;
    private NodeList pizzaList;
    private int indexList;
//./databases/MenuPizze.xml

    public PizzaXmlReader(String path) {

        xmlMenu = MyXmlParser.getDocument(path);
        this.pizzaList = xmlMenu.getElementsByTagName("pizza");// HO LA STRUTTURA NODELIST
        indexList = 0;
    }

    @Override
    public Pizza getNextProduct() throws IOException {//RICORDARE CHE QUESTO VIENE RICHIAMATO PER SECONDO
        Node pizza = pizzaList.item(indexList);
        //Livello <pizza><all children>
        NodeList attrpizza = pizza.getChildNodes();

        String name = attrpizza.item(0).getFirstChild().getNodeValue().trim();

        String price = attrpizza.item(1).getFirstChild().getNodeValue().trim();

        NodeList ingredients = attrpizza.item(2).getChildNodes();

        Pizza newPizza = new Pizza(name, Double.valueOf(price));

        for (int i = 0; i < ingredients.getLength(); i++) {
            String ingName = ingredients.item(i).getFirstChild().getNodeValue().trim();
            newPizza.addIngredient(new Ingredient(name));

        }
        indexList++;

        return newPizza;
    }

    @Override
    public boolean hasNextProduct() throws IOException {//RICORDARE CHE VIENE RICHIAMATO PER PRIMO
        if (this.indexList < pizzaList.getLength()) {

            return true;
        } else {
            return false;
        }
    }

}
