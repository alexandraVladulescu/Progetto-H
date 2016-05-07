/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.order_details_view.edit_view;

import gui.order_details_view.edit_view.remove_ingredient_view.RemoveIngredientFrame;
import gui.order_details_view.edit_view.add_ingredient_view.AddIngredientFrame;
import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
                AddIngredientFrame addIngredientsFrame = new AddIngredientFrame(pizzeria, index);
                addIngredientsFrame.setTitle("Aggiungi ingredienti alla pizza...");
                addIngredientsFrame.setVisible(true);
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
                RemoveIngredientFrame removeIngredientsFrame = new RemoveIngredientFrame(pizzeria, index);
                removeIngredientsFrame.setTitle("Rimuovi ingredienti dalla pizza...");
                removeIngredientsFrame.setVisible(true);
                dispose();
            }
            
});
        this.add(addIngredients);
        this.add(removeIngredients);
        
    }
}
