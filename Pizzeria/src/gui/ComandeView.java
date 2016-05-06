/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Comanda;
import data.Pizzeria;
import gui.comande_view.ShippedComandePanel;
import gui.comande_view.WaitingComandePanel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ComandeView extends JPanel implements Observer{

    private Pizzeria pizzeria;
    private WaitingComandePanel waitingComandePanel;
    private ShippedComandePanel shippedComandePanel;
    
    public ComandeView(Pizzeria pizzeria) {
        this.pizzeria=pizzeria;
        
        setLayout(new BoxLayout(this, WIDTH));
        
        waitingComandePanel = new WaitingComandePanel();
        add(waitingComandePanel);
        
        shippedComandePanel = new ShippedComandePanel();
        add(shippedComandePanel);
        
    }

    @Override
    public void update(Observable o, Object o1) {
        //Rimuovo tutti gli elementi dai pannelli delle comande
        shippedComandePanel.removeAllComande();
        waitingComandePanel.removeAllComande();
        ArrayList<Comanda> cmds = new ArrayList<>();
        cmds  = pizzeria.getComandeManager().getComande();
        for (Comanda cmd : cmds) {
            if(cmd.getTerminated()){
                shippedComandePanel.addComanda(cmd);
            }else{
                waitingComandePanel.addComanda(cmd);
            }
        }
        shippedComandePanel.displayComande();
        waitingComandePanel.displayComande();
    }
    
    
    
}
