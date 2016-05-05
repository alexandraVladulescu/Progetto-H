/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_V1;

import data.Comanda;
import data.Pizzeria;
import gui_V1.comande_view.ShippedComandePanel;
import gui_V1.comande_view.WaitingComandePanel;
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
        ArrayList<Comanda> cmds = new ArrayList<>();
        cmds  = pizzeria.getComandeManager().getComande();
        for (Comanda cmd : cmds) {
            if(cmd.getTerminated()){
                shippedComandePanel.addComanda(cmd);
                shippedComandePanel.displayComande();
            }else{
                waitingComandePanel.addComanda(cmd);
                waitingComandePanel.displayComande();
            }
        }
    }
    
    
    
}
