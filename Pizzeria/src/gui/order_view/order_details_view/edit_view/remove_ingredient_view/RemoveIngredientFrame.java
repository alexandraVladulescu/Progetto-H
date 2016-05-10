package gui.order_view.order_details_view.edit_view.remove_ingredient_view;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Markenos
 */
public class RemoveIngredientFrame extends JFrame {

    //As Singleton
    
    private static RemoveIngredientFrame removeIngredientFrame;
     
    private RemoveIngredientFrame(){
    }
    
    public static RemoveIngredientFrame getIstance(){
        if(removeIngredientFrame == null){
            removeIngredientFrame = new RemoveIngredientFrame();
        }
        return removeIngredientFrame;
    } 
    
    //Il nostro modello
    private Pizzeria pizzeria;
    //L'indice della pizza da modificare
    private int index;

    public void init(String title, Pizzeria pizzeria, int index) {
        this.pizzeria = pizzeria;
        this.index = index;
        this.setTitle(title);
        //Creo il pannello interno
        RemoveIngredientsPanel removeIngredientsPanel = new RemoveIngredientsPanel(pizzeria, index);
        //Creo lo scroller per tale pannello
        JScrollPane scrollerRemoveIngredientsPanel = new JScrollPane(removeIngredientsPanel);
        //Voglio visualizzare sempre la barra verticale di scorrimento
        scrollerRemoveIngredientsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Aggiungo scroller al frame
        this.getContentPane().add(BorderLayout.CENTER, scrollerRemoveIngredientsPanel);
        //Aggiungo anche il pulsante per confermare la rimozione degli ingredienti selezionati
        this.getContentPane().add(BorderLayout.SOUTH, new RemoveIngredientsButton("Rimuovi ingredienti", pizzeria, index, this, removeIngredientsPanel));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
        this.setVisible(true);

    }

}
