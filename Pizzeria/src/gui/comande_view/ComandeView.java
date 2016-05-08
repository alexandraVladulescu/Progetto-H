/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.comande_view;

import gui.comande_view.comande_list_view.ComandeListPanel;
import gui.comande_view.comanda_details_view.ComandaDetailsPanel;
import data.Pizzeria;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author User
 */
public class ComandeView extends JPanel{
    //Il nostro modello
    private Pizzeria pizzeria;

    public ComandeView(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        
        //Layout di una griglia di 1 riga e 2 colonne
        this.setLayout(new GridLayout(1,2));
        
        //Istanzio i pannelli contenuti da ComandeView
        ComandeListPanel comandeListPanel = new ComandeListPanel(pizzeria);
        ComandaDetailsPanel comandaDetailsPanel = new ComandaDetailsPanel(pizzeria,comandeListPanel);
        //Aggiungiamolo tra gli osservatori di ComandeListPanel
        comandeListPanel.registerObserver(comandaDetailsPanel);
        
        //Scroller per il pannello con la lista delle comande
        JScrollPane scrollerComandeListPanel = new JScrollPane(comandeListPanel);
        scrollerComandeListPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //setto che la barra verticale ci sia sempre per evitare l'effetto "saltino" quando essa viene creata...
        //un po come visto nel caso di PizzasOrderedPanel...
        scrollerComandeListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.add(scrollerComandeListPanel);
        this.add(comandaDetailsPanel);
    }
    
    
}
