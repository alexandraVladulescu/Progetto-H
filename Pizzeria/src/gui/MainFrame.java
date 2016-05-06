package gui;

import data.Comanda;
import data.Pizzeria;
import i_o.FormatType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
            pizzeria.loadIngredientsMenu("./databases/ingredienti.txt",FormatType.TXT);
            
            //Carico le pizze da file txt
            pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
            
            //Creo una nuova comanda (comportamento di default all'apertra del programma)
            pizzeria.setCurrentComanda(new Comanda());
            
            //La finestra principale
            JFrame mainFrame = new JFrame("Pizzeria");
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            //Istanzio i pannelli che contiene
            orderView = new OrderView(this.pizzeria);
            comandeView = new ComandeView(this.pizzeria);

            pizzeria.addObserver(comandeView);
            
            //Permette di scegliere tra la sezione "Crea/Modifica ordine" e la sezione "Storico ordini"
            JTabbedPane selectView = new JTabbedPane();
            selectView.addTab("Crea/Modifica ordine", orderView);
            selectView.addTab("Comande Attive", comandeView);

            mainFrame.add(selectView);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

            
        } catch (IOException ex) {
            System.err.println("Errore nel caricamento dei file");
        }

    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
