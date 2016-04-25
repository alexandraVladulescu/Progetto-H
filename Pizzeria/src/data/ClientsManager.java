package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Markenos
 */
public class ClientsManager {
    
    private ArrayList<Client> clients;
    private HashMap<Client, Address> client_address_map;

    public ClientsManager() {
        clients = new ArrayList<>();
        client_address_map = new HashMap<>();
    }
    
    public void addClient(Client client, Address address){
        clients.add(client);
        client_address_map.put(client, address);
    }
    
    
}
