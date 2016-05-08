package gui.order_view;

import gui.order_view.order_details_view.OrderDetailsPanel;
import data.Pizzeria;
import gui.order_view.product_selection_view.PizzaButtonsPanel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Markenos
 */
public class OrderView extends JPanel{

    private Pizzeria pizzeria;

    // Il primo pannello è quello di sinistra contenente l'elenco delle pizze nel menu
    // Il secondo pannello è quello di destra che contiene i dettagli della comanda
    private PizzaButtonsPanel pizzaButtonsPanel;
    private OrderDetailsPanel orderDetailsPanel;

    public OrderView(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;


        //Istanzio i pannelli che contiene OrderView
        pizzaButtonsPanel = new PizzaButtonsPanel(this.pizzeria);
        orderDetailsPanel = new OrderDetailsPanel(this.pizzeria);

        //Scroller per il pannello delle pizze. Disabilitiamo lo scrolling orizzontale
        JScrollPane scrollerPizzasPanel = new JScrollPane(pizzaButtonsPanel);
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

}
