package gui_V1;

import data.Pizzeria;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Markenos
 */
public class OrderView extends JPanel implements Observer {

    private Pizzeria pizzeria;

    // Il primo pannello è quello di sinistra contenente l'elenco delle pizze nel menu
    // Il secondo pannello è quello di destra che contiene i dettagli della comanda
    private PizzasPanel pizzasPanel;
    private OrderDetailsPanel orderDetailsPanel;

    public OrderView(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;

        pizzeria.getCurrentComanda().addObserver(this);

        //Istanzio i pannelli che contiene OrderView
        pizzasPanel = new PizzasPanel(this.pizzeria);
        orderDetailsPanel = new OrderDetailsPanel(this.pizzeria);

        //Scroller per il pannello delle pizze. Disabilitiamo lo scrolling orizzontale
        JScrollPane scrollerPizzasPanel = new JScrollPane(pizzasPanel);
        scrollerPizzasPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //setto che la barra verticale ci sia sempre per evitare l'effetto "saltino" quando essa viene creata...
        //un po come visto nel caso di PizzasOrderedPanel...
        scrollerPizzasPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Per selezionare tra bibite e bevande (aggiungo gli scroller ovviamente...)
        JTabbedPane selectProductType = new JTabbedPane();
        selectProductType.addTab("Pizze", scrollerPizzasPanel);

        //Griglia di una riga e due colonne
        this.setLayout(new GridLayout(1, 2));

        
        
        this.add(selectProductType);
        this.add(orderDetailsPanel);

    }

    @Override
    public void update(Observable o, Object arg) {
        this.orderDetailsPanel.update();
    }

}
