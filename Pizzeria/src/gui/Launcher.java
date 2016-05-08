package gui;

import gui.comande_view.ComandeView;
import data.Comanda;
import data.Pizzeria;
import gui.create_ingredient_view.CreateIngredientFrame;
import gui.order_view.create_pizza_view.CreatePizzaFrame;
import gui.order_view.OrderView;
import gui.order_view.remove_ingredient_view.RemoveIngredientsFromPizzeriaFrame;
import gui.order_view.remove_pizza_view.RemovePizzasFromPizzeriaFrame;
import i_o.FormatType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

/**
 *
 * @author Markenos
 */
public class Launcher {

    //La nostra pizzeria
    private Pizzeria pizzeria;
    //Pannello relativo alla sezione "Crea/Modifica ordine"
    private OrderView orderView;
    private ComandeView comandeView;

    public Launcher() {
        try {
            //Istanziazione della nostra pizzeria
            pizzeria = new Pizzeria();

            //Carico gli ingredienti da file txt
            pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

            //Carico le pizze da file txt
            pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);

            //Creo una nuova comanda (comportamento di default all'apertra del programma)
            pizzeria.getCurrentComandaManager().setCurrentComanda(new Comanda());

            //La finestra principale. E' un singleton!
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.setTitle("Pizzeria");
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //Istanzio i pannelli che contiene
            orderView = new OrderView(this.pizzeria);
            comandeView = new ComandeView(this.pizzeria);


            //Permette di scegliere tra la sezione "Crea/Modifica ordine" e la sezione "Storico ordini"
            JTabbedPane selectView = new JTabbedPane();
            selectView.addTab("Crea/Modifica ordine", orderView);
            selectView.addTab("Comande Attive", comandeView);

            mainFrame.add(selectView);

            //Creazione del menu dal quale si possono aggiungere e rimuovere pizze o ingredienti dalla pizzeria
            JMenuItem addIngredient = new JMenuItem("Aggiungi ingrediente");
            JMenuItem removeIngredient = new JMenuItem("Rimuovi ingrediente");
            JMenuItem addPizza = new JMenuItem("Aggiungi pizza");
            JMenuItem removePizza = new JMenuItem("Rimuovi pizza");

            //Settiamo i listener per i vari menu
            this.setAddIngredientListener(addIngredient);
            this.setRemoveIngredientListener(removeIngredient);
            this.setAddPizzaListener(addPizza);
            this.setRemovePizzaListener(removePizza);

            JMenu menuFile = new JMenu("File");
            menuFile.add(addIngredient);
            menuFile.add(removeIngredient);
            menuFile.add(addPizza);
            menuFile.add(removePizza);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menuFile);
            mainFrame.setJMenuBar(menuBar);

            //settiamo il frame a visible
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

        } catch (IOException ex) {
            System.err.println("Errore nel caricamento dei file");
        }

    }

    private void setAddIngredientListener(JMenuItem addIngredient) {
        //Purtroppo devo usare un ActionListener perchè con il MouseAdapter non funziona...
        addIngredient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateIngredientFrame createIngredientFrame = new CreateIngredientFrame(pizzeria);
                createIngredientFrame.setTitle("Aggiungi un nuovo ingrediente alla pizzeria...");
                createIngredientFrame.setVisible(true);
            }
        });
    }

    private void setRemoveIngredientListener(JMenuItem removeIngredient) {
        //Purtroppo devo usare un ActionListener perchè con il MouseAdapter non funziona...
        removeIngredient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveIngredientsFromPizzeriaFrame removeIngredientsFromPizzeriaFrame = new RemoveIngredientsFromPizzeriaFrame(pizzeria);
                removeIngredientsFromPizzeriaFrame.setTitle("Rimuovi ingredienti dalla pizzeria...");
                removeIngredientsFromPizzeriaFrame.setVisible(true);
            }
        });
    }

    private void setAddPizzaListener(JMenuItem addPizza) {
        addPizza.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePizzaFrame createPizzaFrame = new CreatePizzaFrame(pizzeria);
                createPizzaFrame.setTitle("Creazione di una nuova pizza...");
                createPizzaFrame.setVisible(true);
            }
        });
    }

    private void setRemovePizzaListener(JMenuItem removePizza) {
        removePizza.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RemovePizzasFromPizzeriaFrame removePizzasFromPizzeriaFrame = new RemovePizzasFromPizzeriaFrame(pizzeria);
                removePizzasFromPizzeriaFrame.setTitle("Rimuovi pizze dalla pizzeria...");
                removePizzasFromPizzeriaFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Launcher();
    }
}
