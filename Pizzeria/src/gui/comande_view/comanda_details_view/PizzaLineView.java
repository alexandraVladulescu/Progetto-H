package gui.comande_view.comanda_details_view;

import gui.order_view.order_details_view.*;
import data.Pizza;
import data.Pizzeria;
import exceptions.ProductNotFoundException;
import gui.order_view.order_details_view.edit_view.EditPizzaFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Rappresenta una riga nell'interfaccia grafica avente i seguenti
 * elementi:<br/>
 * 1) Nome della pizza<br/>
 * 2) Prezzo della pizza<br/>
 * 3) Pulsante di modifica della pizza<br/>
 * 4) Pulsante di eliminazione della pizza<br/>
 *
 * @author Markenos
 */
public class PizzaLineView extends JPanel {

    //Ogni riga è relativa ad una pizza
    private Pizza pizza;
    private Pizzeria pizzeria;
    
    private JLabel labelName;
    private JLabel labelPrice;
    
    public PizzaLineView(Pizzeria pizzeria,Pizza pizza, Dimension preferredDimension, int index) {
        this.pizza = pizza;
        this.pizzeria = pizzeria;

        //Dalla dimenzione del genitore otteniamo la dimensione standard
        //di ogni elemento, che è Dimensione_pannello_genitore/10.
        preferredDimension.height = preferredDimension.height / 10;
        //this.setPreferredSize(preferredDimension);
        this.setMinimumSize(preferredDimension);
        this.setMaximumSize(preferredDimension);
        
        labelName = new JLabel(pizza.getFullName());
        labelPrice = new JLabel(Double.toString(pizza.getPrice()));

        //Una riga avente 2 colonne
        this.setLayout(new GridLayout(1, 2));

        //Aggiungo gli elementi al pannello
        this.add(labelName);
        this.add(labelPrice);
    }
    
}
