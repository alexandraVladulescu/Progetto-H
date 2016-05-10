package gui.order_view.order_details_view.edit_view;

import data.Pizzeria;
import gui.order_view.order_details_view.edit_view.add_ingredient_view.AddIngredientFrame;
import gui.order_view.order_details_view.edit_view.remove_ingredient_view.RemoveIngredientFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Questo è il frame che contiene i pannelli per scegliere quali modifiche fare
 * alla pizza. 
 *
 * @author Markenos
 */
public class EditPizzaFrame extends JFrame {

    //La nostra pizzeria
    private Pizzeria pizzeria;
    //L'indice della pizza da modificare
    private int index;

    public EditPizzaFrame(Pizzeria pizzeria, int index) {
        this.pizzeria = pizzeria;
        this.index = index;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
        //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));

        //Impostiamo come layout una griglia di 2 righe e 1 colonna
        this.getContentPane().setLayout(new GridLayout(2, 1));
        
        //Pulsante aggiungi ingredienti
         JButton addIngredients = new JButton("Aggiungi ingredienti");
        //Aggiungo il MouseListener per il pulsante di addIngredients
        addIngredients.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Creo un nuovo frame per scegliere gli ingredienti da aggiungere
                AddIngredientFrame.getIstance().init("Aggiungi ingredienti alla pizza...", pizzeria, index);
                dispose();
            }
            
});
        JButton removeIngredients = new JButton("Rimuovi ingredienti");
        //Aggiungo il listener per il pulsante removeIngredients
        removeIngredients.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Creo un nuovo frame per scegliere gli ingredienti da rimuovere
                RemoveIngredientFrame.getIstance().init("Rimuovi ingredienti dalla pizza...", pizzeria, index);
                dispose();
            }
            
});
        this.add(addIngredients);
        this.add(removeIngredients);
        
    }
}
