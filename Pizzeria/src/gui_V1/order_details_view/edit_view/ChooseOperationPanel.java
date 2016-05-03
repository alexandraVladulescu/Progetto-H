package gui_V1.order_details_view.edit_view;

import data.Pizzeria;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Questo è il pannello contenente i pulsanti per scegliere se aggiungere o eliminare degli ingredienti dalla pizza
 * @author Markenos
 */
public class ChooseOperationPanel extends JPanel {
    private Pizzeria pizzeria;
    //L'indice della pizza che andiamo a modificare...ce lo stiamo portando dietro da un bel pezzo effettivamente
    private int index;
    //Perché passiamo anche il frame genitore? Perché cosi abbiamo un riferimento per chiuderlo all'evento click del mouse...
    private JFrame frameGenitore;

    public ChooseOperationPanel(Pizzeria pizzeria, int index, JFrame frameGenitore) {
        this.pizzeria = pizzeria;
        this.index = index;
        this.frameGenitore = frameGenitore;
        
        //Impostiamo come layout una griglia di 2 righe e 1 colonna
        this.setLayout(new GridLayout(2, 1));
        
        JButton addIngredients = new JButton("Aggiungi ingredienti");
        //Aggiungo il MouseListener per il pulsante di addIngredients
        addIngredients.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Creo un nuovo frame per scegliere gli ingredienti da aggiungere
                JFrame addIngredientsFrame = new JFrame("Scegli gli ingredienti da aggiungere...");
                //Creo il pannello interno
                AddIngredientsPanel addIngredientsPanel = new AddIngredientsPanel(pizzeria);
                //Creo lo scroller per tale pannello
                JScrollPane  scrollerAddIngredientsPanel = new JScrollPane(addIngredientsPanel);
                //Voglio visualizzare sempre la barra verticale di scorrimento
                scrollerAddIngredientsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                //Aggiungo scroller al frame
                addIngredientsFrame.getContentPane().add(BorderLayout.CENTER,scrollerAddIngredientsPanel);
                //Aggiungo anche il pulsante per confermare l'aggiunta degli ingredienti selezionati
                addIngredientsFrame.getContentPane().add(BorderLayout.SOUTH,new AddIngredientsButton("Aggiungi ingredienti",pizzeria,index,addIngredientsFrame,addIngredientsPanel));
                addIngredientsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addIngredientsFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                addIngredientsFrame.setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-((int)addIngredientsFrame.getSize().getWidth()/2), ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-((int)addIngredientsFrame.getSize().getHeight()/2));
                addIngredientsFrame.setVisible(true);
                frameGenitore.dispose();
            }
            
});
        JButton removeIngredients = new JButton("Rimuovi ingredienti");
        //Aggiungo il listener per il pulsante removeIngredients
        removeIngredients.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Creo un nuovo frame per scegliere gli ingredienti da rimuovere
                JFrame removeIngredientsFrame = new JFrame("Scegli gli ingredienti da rimuovere...");
                //Creo il pannello interno
                RemoveIngredientsPanel removeIngredientsPanel = new RemoveIngredientsPanel(pizzeria,index);
                //Creo lo scroller per tale pannello
                JScrollPane  scrollerRemoveIngredientsPanel = new JScrollPane(removeIngredientsPanel);
                //Voglio visualizzare sempre la barra verticale di scorrimento
                scrollerRemoveIngredientsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                //Aggiungo scroller al frame
                removeIngredientsFrame.getContentPane().add(BorderLayout.CENTER,scrollerRemoveIngredientsPanel);
                //Aggiungo anche il pulsante per confermare la rimozione degli ingredienti selezionati
                removeIngredientsFrame.getContentPane().add(BorderLayout.SOUTH,new RemoveIngredientsButton("Rimuovi ingredienti",pizzeria,index,removeIngredientsFrame,removeIngredientsPanel));
                removeIngredientsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeIngredientsFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
                //Per centrarlo sullo schermo...anche se non lo centra in verita...
                //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                removeIngredientsFrame.setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-((int)removeIngredientsFrame.getSize().getWidth()/2), ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-((int)removeIngredientsFrame.getSize().getHeight()/2));
                removeIngredientsFrame.setVisible(true);
                frameGenitore.dispose();
            }
            
});
        this.add(addIngredients);
        this.add(removeIngredients);
        
    }
    
    
    
}
