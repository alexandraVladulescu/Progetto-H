package gui.comande_view.comanda_details_view;

import data.Comanda;
import gui.order_view.order_details_view.*;
import data.CurrentComandaManager;
import data.Pizzeria;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Markenos
 */
public class ComandaTotalPricePanel extends JPanel {
    private Pizzeria pizzeria;
    private JLabel labelDescription;
    private JLabel labelTotalPrice;

    public ComandaTotalPricePanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        
        //Imposto una griglia di una riga e una colonna: la prima per la scritta "Totale" la seconda per il prezzo
        this.setLayout(new GridLayout(1,2));
        
        //Istanzio le due label
        labelDescription = new JLabel("Totale comanda:");
        labelTotalPrice = new JLabel("");
        
        //Aggiungo le label al pannello
        this.add(labelDescription);
        this.add(labelTotalPrice);
        
        
    }
    
    public void update(Comanda comanda){
        labelTotalPrice.setText(Double.toString(comanda.calculateTotalPrice()));
    }
    
    
}
