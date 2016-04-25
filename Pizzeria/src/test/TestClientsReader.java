/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.org.apache.xml.internal.security.c14n.helper.C14nHelper;
import data.Client;
import i_o_V1.ClientsXmlReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TestClientsReader {
    
    public static void main(String[] args) {
        
        ClientsXmlReader reader = new ClientsXmlReader("./databases/ClientsDB.xml");
        
        try {
            Client c1 = reader.getNextClient();
            Client c2 = reader.getNextClient();
            Client c3 = reader.getNextClient();
            Client c4 = reader.getNextClient();
            
            System.out.println(c1.toString()+"\n");
            System.out.println(c2.toString()+"\n");
            System.out.println(c3.toString()+"\n");
            System.out.println(c4.toString()+"\n");
        } catch (IOException ex) {
            Logger.getLogger(TestClientsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
