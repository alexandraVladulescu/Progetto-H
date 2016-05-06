package gui;

import data.Comanda;
import data.Pizzeria;
import gui.create_ingredient_view.CreateIngredientButton;
import gui.create_ingredient_view.CreateIngredientPanel;
import gui.create_pizza_view.CreatePizzaButton;
import gui.create_pizza_view.PizzaDetailsPanel;
import gui.create_pizza_view.PizzasListPanel;
import gui.remove_ingredient_view.RemoveIngredientsFromPizzeriaButton;
import gui.remove_ingredient_view.RemoveIngredientsFromPizzeriaPanel;
import gui.remove_pizza_view.RemovePizzasFromPizzeriaButton;
import gui.remove_pizza_view.RemovePizzasFromPizzeriaPanel;
import i_o.FormatType;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Markenos
 */
public class MainFrame {

    //La nostra pizzeria
    private Pizzeria pizzeria;
    //Pannello relativo alla sezione "Crea/Modifica ordine"
    private OrderView orderView;
    private ComandeView comandeView;

    public MainFrame() {
        try {
            //Istanziazione della nostra pizzeria
            pizzeria = new Pizzeria();

            //Carico gli ingredienti da file txt
            pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

            //Carico le pizze da file txt
            pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);

            //Creo una nuova comanda (comportamento di default all'apertra del programma)
            pizzeria.getCurrentComandaManager().setCurrentComanda(new Comanda());

            //La finestra principale
            JFrame mainFrame = new JFrame("Pizzeria");
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //Istanzio i pannelli che contiene
            orderView = new OrderView(this.pizzeria);
            comandeView = new ComandeView(this.pizzeria);

            pizzeria.getCurrentComandaManager().addObserver(comandeView);

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
                JFrame addIngredientFrame = new JFrame("Aggiungi un nuovo ingrediente alla pizzeria...");
                //Aggiungo il pannello con il form dell'ingrediente da aggiungere
                CreateIngredientPanel createIngredientPanel = new CreateIngredientPanel();
                addIngredientFrame.getContentPane().add(BorderLayout.CENTER, createIngredientPanel);
                //Aggiungo il pulsante di creazione del nuovo ingrediente
                addIngredientFrame.getContentPane().add(BorderLayout.SOUTH, new CreateIngredientButton("Crea ingrediente", pizzeria, addIngredientFrame, createIngredientPanel));
                addIngredientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addIngredientFrame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                addIngredientFrame.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) addIngredientFrame.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) addIngredientFrame.getSize().getHeight() / 2));
                addIngredientFrame.setVisible(true);
            }
        });
    }

    private void setRemoveIngredientListener(JMenuItem removeIngredient) {
        //Purtroppo devo usare un ActionListener perchè con il MouseAdapter non funziona...
        removeIngredient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame removeIngredientFrame = new JFrame("Rimuovi ingredienti dalla pizzeria...");
                //Aggiungo il pannello con l'elenco degli ingredienti da eliminare con relativo scroller
                RemoveIngredientsFromPizzeriaPanel removeIngredientsFromPizzeriaPanel = new RemoveIngredientsFromPizzeriaPanel(pizzeria);
                JScrollPane scrollerRemoveIngredientsFromPizzeriaPanel = new JScrollPane(removeIngredientsFromPizzeriaPanel);
                removeIngredientFrame.getContentPane().add(BorderLayout.CENTER, scrollerRemoveIngredientsFromPizzeriaPanel);
                //Aggiungo il pulsante di rimozione degli ingredienti
                removeIngredientFrame.getContentPane().add(BorderLayout.SOUTH, new RemoveIngredientsFromPizzeriaButton("Rimuovi ingredienti", pizzeria, removeIngredientFrame, removeIngredientsFromPizzeriaPanel));
                removeIngredientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeIngredientFrame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                removeIngredientFrame.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) removeIngredientFrame.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) removeIngredientFrame.getSize().getHeight() / 2));
                removeIngredientFrame.setVisible(true);
            }
        });
    }

    private void setAddPizzaListener(JMenuItem addPizza) {
        addPizza.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame createPizzaFrame = new JFrame("Creazione di una nuova pizza...");
                //Aggiungo il pannello con i form per l'inserimento del nome e del prezzo della pizza da creare
                PizzaDetailsPanel pizzaDetailsPanel = new PizzaDetailsPanel();
                createPizzaFrame.add(BorderLayout.NORTH, pizzaDetailsPanel);
                //Aggiungo il pannello con l'elenco degli ingredienti da eliminare con relativo scroller
                PizzasListPanel pizzasListPanel = new PizzasListPanel(pizzeria);
                JScrollPane scrollerPizzasListPanel = new JScrollPane(pizzasListPanel);
                createPizzaFrame.getContentPane().add(BorderLayout.CENTER, scrollerPizzasListPanel);
                //Aggiungo il pulsante di rimozione degli ingredienti
                createPizzaFrame.getContentPane().add(BorderLayout.SOUTH, new CreatePizzaButton("Crea pizza", pizzeria, createPizzaFrame, pizzaDetailsPanel, pizzasListPanel));
                createPizzaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createPizzaFrame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                createPizzaFrame.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) createPizzaFrame.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) createPizzaFrame.getSize().getHeight() / 2));
                createPizzaFrame.setVisible(true);
            }
        });
    }

    private void setRemovePizzaListener(JMenuItem removePizza) {
        removePizza.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame removePizzaFrame = new JFrame("Rimuovi pizze dalla pizzeria...");
                //Aggiungo il pannello con l'elenco delle pizze da eliminare con relativo scroller
                RemovePizzasFromPizzeriaPanel removePizzasFromPizzeriaPanel = new RemovePizzasFromPizzeriaPanel(pizzeria);
                JScrollPane scrollerRemovePizzasFromPizzeriaPanel = new JScrollPane(removePizzasFromPizzeriaPanel);
                removePizzaFrame.getContentPane().add(BorderLayout.CENTER, scrollerRemovePizzasFromPizzeriaPanel);
                //Aggiungo il pulsante di rimozione delle pizze
                removePizzaFrame.getContentPane().add(BorderLayout.SOUTH, new RemovePizzasFromPizzeriaButton("Rimuovi pizze", pizzeria, removePizzaFrame, removePizzasFromPizzeriaPanel));
                removePizzaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removePizzaFrame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                removePizzaFrame.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) removePizzaFrame.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) removePizzaFrame.getSize().getHeight() / 2));
                removePizzaFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
