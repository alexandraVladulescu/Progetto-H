/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_V1.comande_view;

import data.Comanda;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ComandePanel extends JPanel{
    
    private ArrayList<Comanda> comande;

    public ComandePanel() {
       //setLayout(new BoxLayout(this, HEIGHT));
        
        comande = new ArrayList<>();
    }
    
    public void addComanda(Comanda comanda){
        comande.add(comanda);
    }

    public ArrayList<Comanda> getComande() {
        return comande;
    }
    
    public void displayComande(){
        for (Comanda comanda : comande) {
            JTextField txtFld = new JTextField(comanda.toString());
            txtFld.setEditable(false);
            add(txtFld);
            //TODO: aggiungere un MouseListener che al click ti riporta alla schermata ordini
        }
    }
    
}
