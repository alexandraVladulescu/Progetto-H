package gui.order_view.remove_ingredient_view;

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
public class RemoveIngredientsFromPizzeriaFrame extends JFrame {
    
    //As Singleton
    private static RemoveIngredientsFromPizzeriaFrame removeIngredientsFromPizzeriaFrame;

    private RemoveIngredientsFromPizzeriaFrame() throws HeadlessException {
    }
    
    public static RemoveIngredientsFromPizzeriaFrame getIstance(){
        if(removeIngredientsFromPizzeriaFrame==null){
            removeIngredientsFromPizzeriaFrame = new RemoveIngredientsFromPizzeriaFrame();
        }
        return removeIngredientsFromPizzeriaFrame;
    }
    
    //As Class
    
    //Il nostro modello
    private Pizzeria pizzeria;

    public void init(String title, Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.setTitle(title);
        
        //Aggiungo il pannello con l'elenco degli ingredienti da eliminare con relativo scroller
                RemoveIngredientsFromPizzeriaPanel removeIngredientsFromPizzeriaPanel = new RemoveIngredientsFromPizzeriaPanel(pizzeria);
                JScrollPane scrollerRemoveIngredientsFromPizzeriaPanel = new JScrollPane(removeIngredientsFromPizzeriaPanel);
                this.getContentPane().add(BorderLayout.CENTER, scrollerRemoveIngredientsFromPizzeriaPanel);
                //Aggiungo il pulsante di rimozione degli ingredienti
                this.getContentPane().add(BorderLayout.SOUTH, new RemoveIngredientsFromPizzeriaButton("Rimuovi ingredienti", pizzeria, removeIngredientsFromPizzeriaPanel));
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
                this.setVisible(true);
    }
    
    
}
