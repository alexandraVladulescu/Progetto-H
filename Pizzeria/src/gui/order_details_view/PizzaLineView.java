package gui.order_details_view;

import data.Pizza;
import data.Pizzeria;
import exceptions.ProductNotFoundException;
import gui.order_details_view.edit_view.ChooseOperationPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
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
                JFrame chooseOperationFrame = new JFrame("Modifica pizza");
                chooseOperationFrame.getContentPane().add(new ChooseOperationPanel(pizzeria,index,chooseOperationFrame));
                chooseOperationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                chooseOperationFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                chooseOperationFrame.setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-((int)chooseOperationFrame.getSize().getWidth()/2), ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-((int)chooseOperationFrame.getSize().getHeight()/2));
                chooseOperationFrame.setVisible(true);
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
                    pizzeria.getCurrentComandaManager().getCurrentComanda().removeProduct(index);
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
