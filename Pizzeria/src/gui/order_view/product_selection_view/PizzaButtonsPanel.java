package gui.order_view.product_selection_view;

import data.Pizza;
import data.Pizzeria;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Markenos
 */
public class PizzaButtonsPanel extends JPanel implements Observer {

    private Pizzeria pizzeria;
    private static final int NUMERO_COLONNE = 4;

    public PizzaButtonsPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.pizzeria.getMenuPizze().addObserver(this);

        //Ottengo il numero di pizze nel menù e quindi il  numero di linee
        //tenendo da conto che al massimo il pannello ha 3 colonne (dal numero di colonne si determina il numero di righe...)
        int n = pizzeria.getMenuPizze().getMenuSize();
        int numberLine = (int) Math.ceil( ((double)n) / ((double)NUMERO_COLONNE));

        //Utilizzo un BoxLayout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createPizzasGrid(n, numberLine);

    }

    private void createPizzasGrid(int n, int numberLine) {
        //Ottengo un ArrayList di numberLine righe. Ogni riga contiene i pulsanti che rappresentano le pizze
        ArrayList<PizzaButtonsPanelLine> rows = new ArrayList<PizzaButtonsPanelLine>();

        //Istanzio una variabile che indica l'indice cui siamo arrivati a scorrere tra le n pizze
        int index = 0;

        //Per ogni riga contenente i pulsanti...
        for (int i = 0; i < numberLine; i++) {
            //Creo una nuova linea contenente i pulsanti
            PizzaButtonsPanelLine pizzaLinePanel = new PizzaButtonsPanelLine(pizzeria, NUMERO_COLONNE);
            rows.add(pizzaLinePanel);
            //Finché c'è spazio in pizzaLinePanel per aggiungere pulsanti (cioè pizze...)
            while (pizzaLinePanel.hasNext()) {
                if (index < n) {
                    pizzaLinePanel.addPizzaButton(index);
                    index++;
                } else{
                    break;  //FA VERAMENTE SCHIFO LO SO!
                }
            }
            this.add(rows.get(i));
        }
       
    }

    //L'aggiornamento di questa schermata avviene quando aggiungiamo o rimuoviamo una pizza dalla pizzeria
    @Override
    public void update(Observable o, Object arg) {
        this.removeAll();
        int n = pizzeria.getMenuPizze().getMenuSize();
        int numberLine = (int) Math.ceil( ((double)n) / ((double)NUMERO_COLONNE));

        //Utilizzo un BoxLayout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createPizzasGrid(n, numberLine);
    }
    
    

}
