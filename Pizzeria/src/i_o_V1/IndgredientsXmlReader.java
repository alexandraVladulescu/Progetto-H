/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o_V1;

import data.Address;
import data.Client;
import data.Ingredient;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author User
 */
public class IndgredientsXmlReader {
    
    private Document ingredientsList;
    private NodeList ingredientsNodeList;
    private int indexList;
  
    //./databases/ClientsDB.xml
    public IndgredientsXmlReader(String path) {

        ingredientsList = MyXmlParser.getDocument(path);
        this.ingredientsNodeList = ingredientsList.getElementsByTagName("cliente");// HO LA STRUTTURA NODELIST
        indexList = 0;
    }
    
    public Ingredient getNextIngredient() throws IOException {//RICORDARE CHE QUESTO VIENE RICHIAMATO PER SECONDO
        Node ingredient = ingredientsNodeList.item(indexList);
        //Livello <ingrediente>*
        NodeList ingredientAttr = ingredient.getChildNodes();
        
        String name = ingredientAttr.item(0).getFirstChild().getNodeValue().trim();
        
        String price = ingredientAttr.item(1).getFirstChild().getNodeValue().trim();
              
        indexList++;
        
        return new Ingredient(name, Double.valueOf(price));
    }

    public boolean hasNextProduct() throws IOException {//RICORDARE CHE VIENE RICHIAMATO PER PRIMO
        if (this.indexList < ingredientsNodeList.getLength()) {

            return true;
        } else {
            return false;
        }
    }
    
}
