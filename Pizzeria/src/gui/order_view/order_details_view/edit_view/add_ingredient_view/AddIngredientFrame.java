package gui.order_view.order_details_view.edit_view.add_ingredient_view;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import sun.security.jca.GetInstance;

/**
 *
 * @author Markenos
 */
public class AddIngredientFrame extends JFrame {

    //As Singleton 
    private static AddIngredientFrame addIngredientFrame;

    private AddIngredientFrame() {
        
    }
    
    public static AddIngredientFrame getIstance(){
        if(addIngredientFrame==null){
            addIngredientFrame = new AddIngredientFrame();
        }
        return addIngredientFrame;
    }
    
    //As Class 
    
    //Il nostro modello
    private Pizzeria pizzeria;
    //L'indice della pizza da modificare
    private int index;

    public void init(String title, Pizzeria pizzeria, int index){
        this.pizzeria=pizzeria;
        this.index=index;
        this.setTitle(title);
        //Creo il pannello interno
        AddIngredientsPanel addIngredientsPanel = new AddIngredientsPanel(pizzeria, index);
        //Creo lo scroller per tale pannello
        JScrollPane scrollerAddIngredientsPanel = new JScrollPane(addIngredientsPanel);
        //Voglio visualizzare sempre la barra verticale di scorrimento
        scrollerAddIngredientsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Aggiungo scroller al frame
        this.getContentPane().add(BorderLayout.CENTER, scrollerAddIngredientsPanel);
        //Aggiungo anche il pulsante per confermare l'aggiunta degli ingredienti selezionati
        this.getContentPane().add(BorderLayout.SOUTH, new AddIngredientsButton("Aggiungi ingredienti", pizzeria, index, addIngredientsPanel));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
        this.setVisible(true);
    }
    
    public static void disposeFrame(){
        addIngredientFrame.dispose();
        addIngredientFrame = null;
    }
    

}
