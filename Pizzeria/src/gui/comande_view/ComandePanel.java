/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.comande_view;

import data.Comanda;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ComandePanel extends JPanel{
    
    private HashMap<Comanda, JTextField> comandeMap;

    public ComandePanel() {
       //setLayout(new BoxLayout(this, HEIGHT));
        
        comandeMap = new HashMap<>();
    }
    
    public void addComanda(Comanda comanda){
        comandeMap.put(comanda, new JTextField(comanda.toString()));
    }

    public HashMap<Comanda, JTextField> getComandeMap() {
        return comandeMap;
    }

    public void displayComande(){
        for (Comanda cmd : comandeMap.keySet()) {
            comandeMap.get(cmd).setEditable(false);
            add(comandeMap.get(cmd));
            //TODO : Aggiungere MouseListener per riportare alla schermata ordini
        }
    }
    
    public void removeAllComande(){
        comandeMap.clear();
    }
    
}
