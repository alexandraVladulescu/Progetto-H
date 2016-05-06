package gui.order_details_view;

import data.CurrentComandaManager;
import data.Pizzeria;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *DrinksTotalPricePanel e PizzasTotalPricePanel hanno codice dupplicato, fai una superclasse
 * @author Markenos
 */
public class DrinksTotalPricePanel extends JPanel {
    
    private Pizzeria pizzeria;
    private CurrentComandaManager currentComandaManager;
    private JLabel labelDescription;
    private JLabel labelTotalPrice;

    public DrinksTotalPricePanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        currentComandaManager = pizzeria.getCurrentComandaManager();
        
        setBackground(new Color(180, 238, 180));
        //Imposto una griglia di una riga e una colonna: la prima per la scritta "Totale" la seconda per il prezzo
        this.setLayout(new GridLayout(1,2));
        
        //Istanzio le due label
        labelDescription = new JLabel("Totale bibite:");
        labelTotalPrice = new JLabel(Double.toString(currentComandaManager.getCurrentComanda().calculateTotalPrice()));
        
        //Aggiungo le label al pannello
        this.add(labelDescription);
        this.add(labelTotalPrice);
        
        
    }
    
    public void update(){
        labelTotalPrice.setText(Double.toString(currentComandaManager.getCurrentComanda().calculateTotalPrice()));
    }
    
    
}
