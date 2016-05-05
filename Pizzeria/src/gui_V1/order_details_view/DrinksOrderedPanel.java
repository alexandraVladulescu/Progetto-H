package gui_V1.order_details_view;

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
        
        setBackground(new Color(193, 255, 193));
    }
    
    
    
}
