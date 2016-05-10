package gui.order_view.create_pizza_view;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Questo frame contiene il form per aggiungere una pizza alla pizzeria.
 *
 * @author Markenos
 */
public class CreatePizzaFrame extends JFrame {
    
    //As Singleton
    private static CreatePizzaFrame createPizzaFrame;

    private CreatePizzaFrame(){
    }
    
    public static CreatePizzaFrame getIstance(){
        if(createPizzaFrame==null){
            createPizzaFrame = new CreatePizzaFrame();
        }
        return createPizzaFrame;
    }
    
    //As Class
    
    //Il nostro modello
    private Pizzeria pizzeria;

    public void init(String title, Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.setTitle(title);
        //Aggiungo il pannello con i form per l'inserimento del nome e del prezzo della pizza da creare
        PizzaDetailsPanel pizzaDetailsPanel = new PizzaDetailsPanel();
        this.add(BorderLayout.NORTH, pizzaDetailsPanel);
        //Aggiungo il pannello con l'elenco degli ingredienti da eliminare con relativo scroller
        IngredientsListPanel pizzasListPanel = new IngredientsListPanel(pizzeria);
        JScrollPane scrollerPizzasListPanel = new JScrollPane(pizzasListPanel);
        this.getContentPane().add(BorderLayout.CENTER, scrollerPizzasListPanel);
        //Aggiungo il pulsante di rimozione degli ingredienti
        this.getContentPane().add(BorderLayout.SOUTH, new CreatePizzaButton("Crea pizza", pizzeria, pizzaDetailsPanel, pizzasListPanel));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
        this.setVisible(true);
    }

}
