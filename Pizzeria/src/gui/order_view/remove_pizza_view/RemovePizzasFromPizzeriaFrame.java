package gui.order_view.remove_pizza_view;


import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Markenos
 */
public class RemovePizzasFromPizzeriaFrame extends JFrame {

    //As Singleton
    private static RemovePizzasFromPizzeriaFrame removePizzasFromPizzeriaFrame;
    
    private RemovePizzasFromPizzeriaFrame(){
    }
    
    public static RemovePizzasFromPizzeriaFrame getIstance(){
        if(removePizzasFromPizzeriaFrame==null){
            removePizzasFromPizzeriaFrame = new RemovePizzasFromPizzeriaFrame();
        }
        return removePizzasFromPizzeriaFrame;
    }
    
    //As Class
    //Il nostro modello

    private Pizzeria pizzeria;

    public void init(String title, Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.setTitle(title);

        //Aggiungo il pannello con l'elenco delle pizze da eliminare con relativo scroller
        RemovePizzasFromPizzeriaPanel removePizzasFromPizzeriaPanel = new RemovePizzasFromPizzeriaPanel(pizzeria);
        JScrollPane scrollerRemovePizzasFromPizzeriaPanel = new JScrollPane(removePizzasFromPizzeriaPanel);
        this.getContentPane().add(BorderLayout.CENTER, scrollerRemovePizzasFromPizzeriaPanel);
        //Aggiungo il pulsante di rimozione delle pizze
        this.getContentPane().add(BorderLayout.SOUTH, new RemovePizzasFromPizzeriaButton("Rimuovi pizze", pizzeria, removePizzasFromPizzeriaPanel));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
        //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
        //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
        this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) this.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) this.getSize().getHeight() / 2));
        this.setVisible(true);
    }

}
