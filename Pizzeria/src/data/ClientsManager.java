package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Markenos
 */
public class ClientsManager {
    
    private ArrayList<Client> clients;
  

    public ClientsManager() {
        clients = new ArrayList<>();
      
    }
    
    public void addClient(Client client, Address address){
        clients.add(client);
       
    }
    
    
}
