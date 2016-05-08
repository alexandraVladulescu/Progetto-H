package gui.order_view.order_details_view;

import data.Pizza;
import data.Pizzeria;
import data.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Questo pannello contiene l'elenco delle pizze ordinate
 * per quanto riguarda la comanda corrente
 * @author Markenos
 */
public class PizzasOrderedPanel extends JPanel {
    private Pizzeria pizzeria;
    
    //Ogni elemento di questa ArrayList rappresenta una pizza con relativi pulsanti di modifica ed eliminazione
    ArrayList<PizzaLineView> pizzasLineView;

    public PizzasOrderedPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        pizzasLineView = new ArrayList<PizzaLineView>();
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    
    private void createPizzaLine(Pizza pizza){
        //Alla PizzaLineView viene passata anche la dimensione del pannello genitore
        //Leggi in PizzaLineView.java il perché di ciò...
        //Inoltre devo passargli anche l'indice che la riga ha all'intern di pizzasLineView
        //Ciò serve per permettere poi alla riga di autoeliminarsi quando clicco sul pulsante delete
        this.pizzasLineView.add(new PizzaLineView(pizzeria,pizza,this.getSize(),pizzasLineView.size()));
        this.add(pizzasLineView.get(pizzasLineView.size()-1));
    }
    
    public void update(){
        this.removeAll();
        //L'arrayList va riazzerata anche lei così che abbia dimensione 0 di default
        //sennò avrebbe la dimenisone che avrebbe prima...
        this.pizzasLineView = new ArrayList<PizzaLineView>();
        this.updateUI();
        for(Product pizza : pizzeria.getCurrentComandaManager().getCurrentComanda().getOrdersList()){
            this.createPizzaLine((Pizza) pizza);
        }
    }
}
