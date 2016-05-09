package gui.comande_view.comanda_details_view;

import gui.order_view.order_details_view.*;
import data.Pizzeria;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *DrinksTotalPricePanel e PizzasTotalPricePanel hanno codice dupplicato, fai una superclasse
 * @author Markenos
 */
public class DrinksTotalPricePanel extends JPanel {
    
    private Pizzeria pizzeria;
    private JLabel labelDescription;
    private JLabel labelTotalPrice;
    
    public DrinksTotalPricePanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        
        //Imposto una griglia di una riga e una colonna: la prima per la scritta "Totale" la seconda per il prezzo
        this.setLayout(new GridLayout(1,2));
        
        //Istanzio le due label
        labelDescription = new JLabel("Totale bibite:");
        labelTotalPrice = new JLabel("");
        
        //Aggiungo le label al pannello
        this.add(labelDescription);
        this.add(labelTotalPrice);
        
        
    }
    
    public void update(int selectedComandaIndex){
        labelTotalPrice.setText(Double.toString(pizzeria.getCurrentComandaManager().getComandeManager().getComande().get(selectedComandaIndex).calculateTotalPrice()));
    }
    
    
}
