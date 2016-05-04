/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.client_reader;

import data.Address;
import data.Client;
import data.Pizza;
import i_o.MyXmlParser;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author User
 */
public class ClientXmlReader {
    
    private Document clientsBook;
    private NodeList clientsList;
    private int indexList;
//./databases/ClientsDB.xml
    public ClientXmlReader(String path) {

        clientsBook = MyXmlParser.getDocument(path);
        this.clientsList = clientsBook.getElementsByTagName("cliente");// HO LA STRUTTURA NODELIST
        indexList = 0;
    }
    
    public Client getNextClient() throws IOException {//RICORDARE CHE QUESTO VIENE RICHIAMATO PER SECONDO
        Node cliente = clientsList.item(indexList);
        //Livello <pizza>*
        NodeList clientAttributes = cliente.getChildNodes();

        String name = clientAttributes.item(0).getFirstChild().getNodeValue().trim();

        String cognome = clientAttributes.item(1).getFirstChild().getNodeValue().trim();
        
        String telefono = clientAttributes.item(2).getFirstChild().getNodeValue().trim();
        
        Node address = clientAttributes.item(3);
        
        NodeList addressFields = address.getChildNodes();
        
        String city = addressFields.item(0).getFirstChild().getNodeValue().trim();
        
        String street = addressFields.item(1).getFirstChild().getNodeValue().trim();
        
        String number = addressFields.item(2).getFirstChild().getNodeValue().trim();

        indexList++;
        
        return new Client(name, cognome, telefono, new Address(city, street, number));
    }

    public boolean hasNextProduct() throws IOException {//RICORDARE CHE VIENE RICHIAMATO PER PRIMO
        if (this.indexList < clientsList.getLength()) {

            return true;
        } else {
            return false;
        }
    }
}
