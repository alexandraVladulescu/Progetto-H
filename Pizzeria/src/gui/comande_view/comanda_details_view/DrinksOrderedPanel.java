package gui.comande_view.comanda_details_view;

import data.Comanda;
import gui.order_view.order_details_view.*;
import data.Pizzeria;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * Questo pannello contiente i dettagli delle bevande ordinate
 * per quanto riguarda la comanda corrente
 * @author Markenos
 */
public class DrinksOrderedPanel extends JPanel{
    private Pizzeria pizzeria;

    public DrinksOrderedPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        
    }
    
    
    public void update(Comanda comanda){
        
    }
    
    
}
