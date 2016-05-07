package gui.order_details_view;

import data.Pizza;
import data.Pizzeria;
import exceptions.ProductNotFoundException;
import gui.order_details_view.edit_view.EditPizzaFrame;
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
    //Indica l'indice di questa linea all'interno del pannello pizzasOrderedPanel, che è il suo genitore
    private int index;
    
    private JLabel labelName;
    private JLabel labelPrice;
    private JButton buttonEdit;
    private JButton buttonDelete;
    
    public PizzaLineView(Pizzeria pizzeria,Pizza pizza, Dimension preferredDimension, int index) {
        this.pizza = pizza;
        this.pizzeria = pizzeria;
        this.index  = index;

        //Dalla dimenzione del genitore otteniamo la dimensione standard
        //di ogni elemento, che è Dimensione_pannello_genitore/10.
        preferredDimension.height = preferredDimension.height / 10;
        //this.setPreferredSize(preferredDimension);
        this.setMinimumSize(preferredDimension);
        this.setMaximumSize(preferredDimension);
        
        labelName = new JLabel(pizza.getFullName());
        labelPrice = new JLabel(Double.toString(pizza.getPrice()));
        buttonEdit = new JButton("Edit");
        //Aggiungo il listener per quando facciamo click sul pulsante
        buttonEdit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Creiamo un nuovo frame per scegliere se cambiare la pizza o aggiungere solo degli ingredienti
                EditPizzaFrame editPizzaFrame = new EditPizzaFrame(pizzeria, index);
                editPizzaFrame.setTitle("Modifica pizza");
                editPizzaFrame.setVisible(true);
            }
            
});
        buttonDelete = new JButton("Delete");
        //Aggiungo il listener per quando facciamo click sul pulsante...
        buttonDelete.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                try {
                    System.out.println(index);
                    pizzeria.getCurrentComandaManager().removePizza(index);
                } catch (ProductNotFoundException ex) {
                    System.err.println("Il prodotto che vuoi eliminare non esiste");
                }
            }

        });

        //Una riga avente 4 colonne
        this.setLayout(new GridLayout(1, 4));

        //Aggiungo gli elementi al pannello
        this.add(labelName);
        this.add(labelPrice);
        this.add(buttonEdit);
        this.add(buttonDelete);
    }
    
}
