package gui_V1.product_selection_view;

import data.MenuPizze;
import data.Pizza;
import data.Pizzeria;
import data.Product;
import exceptions.ProductNotFoundException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Questa classe rappresenta una riga all'interno di PizzasPanel. Una riga
 * contiene fino ad un massimo di 5 pizze. Per ogni pizza in questa linea è
 * presente un pulsante.
 *
 * @author Markenos
 */
public class PizzaButtonsPanelLine extends JPanel {

    private JButton[] pizzas;
    private Pizzeria pizzeria;
    private int numeroColonne;
    private int internalIndex;

    public PizzaButtonsPanelLine(Pizzeria pizzeria, int numeroColonne) {
        this.pizzeria = pizzeria;
        this.numeroColonne = numeroColonne;
        pizzas = new JButton[this.numeroColonne];

        //Inizializzo l'indice interno a 0...
        this.internalIndex = 0;

        //Per la larghezza non vogliamo limiti di dimensione, non ci interessa...
        //Per l'altezza prendiamo la dimensione dello schermo e la vividiamo per 8 (un valore per il quale viene fuori una cosa decente)...
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8)));

        //Una riga e 5 colonne
        this.setLayout(new GridLayout(1, this.numeroColonne));
    }

    public boolean hasNext() {
        if (internalIndex < pizzas.length) {
            return true;
        } else {
            return false;
        }

    }

    public void addPizzaButton(int externalIndex) {
        //Lavoro su una copia del menù, altrimenti modifico sempre e solo la stessa pizza!
        MenuPizze tempMenuPizze;
        try {
            tempMenuPizze = (MenuPizze) pizzeria.getMenuPizze().clone();
            Pizza pizza = tempMenuPizze.getPizze().get(externalIndex);
            pizzas[internalIndex] = new JButton(pizza.getName());
            pizzas[internalIndex].setBackground(new Color(180, 238, 180));
            //Aggiungo il listener a ciascun pulsante rappresentante una pizza
            pizzas[internalIndex].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    try {
                        pizzeria.getCurrentComanda().addProduct((Product) pizza.clone());
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("Errore durante la clonazione della pizza in PizzasLinePanel");
                    }
                    System.out.println("Pizza aggiunta: " + pizza.getFullName()); //ai fini di debug...

                }

            });
            this.add(pizzas[internalIndex]);
            this.internalIndex += 1;
        } catch (CloneNotSupportedException ex) {
            System.err.println("Errore nella clonazione del MenùPizze durante la creazione di PizzasPanel...");
        }

    }

}
