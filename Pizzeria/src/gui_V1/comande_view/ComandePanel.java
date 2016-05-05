/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_V1.comande_view;

import data.Comanda;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ComandePanel extends JPanel{
    
    private ArrayList<Comanda> comande;

    public ComandePanel() {
        comande = new ArrayList<>();
    }
    
    public void addComanda(Comanda comanda){
        comande.add(comanda);
    }

    public ArrayList<Comanda> getComande() {
        return comande;
    }
    
}
