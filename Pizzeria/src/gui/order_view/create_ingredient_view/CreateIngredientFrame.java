package gui.create_ingredient_view;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Questo è il frame dal quale possiamo aggiungere un nuovo ingrediente alla
 * pizzeria
 *
 * @author Markenos
 */
public class CreateIngredientFrame extends JFrame {
    //As singleton
    private static CreateIngredientFrame createIngredientFrame;

    private CreateIngredientFrame(){
    }
    
    public static CreateIngredientFrame getIstance(){
        if(createIngredientFrame==null ){
            createIngredientFrame = new CreateIngredientFrame();
        }
        return createIngredientFrame;
    }
    
    //As Class
    
    //Il nostro modello
    private Pizzeria pizzeria;

    public void init(String title, Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.setTitle(title);

        //Aggiungo il pannello con il form dell'ingrediente da aggiungere
        CreateIngredientPanel createIngredientPanel = new CreateIngredientPanel();
        this.getContentPane().add(BorderLayout.CENTER, createIngredientPanel);
        //Aggiungo il pulsante di creazione del nuovo ingrediente
        this.getContentPane().add(BorderLayout.SOUTH, new CreateIngredientButton("Crea ingrediente", pizzeria, this, createIngredientPanel));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
        this.setVisible(true);
    }

}
