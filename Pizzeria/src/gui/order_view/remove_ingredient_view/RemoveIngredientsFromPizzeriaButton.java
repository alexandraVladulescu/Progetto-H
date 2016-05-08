package gui.order_view.remove_ingredient_view;

import data.Pizzeria;
import exceptions.IngredientNotFoundException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Markenos
 */
public class RemoveIngredientsFromPizzeriaButton extends JButton {

    private Pizzeria pizzeria;
    //Questa variabile serve per chiudere il frame genitore...
    private JFrame frameGenitore;
    //Il pannello contenente il form da cui preleviamo i dati da inserire per il nuovo ingrediente
    private RemoveIngredientsFromPizzeriaPanel removeIngredientsFromPizzeriaPanel;

    public RemoveIngredientsFromPizzeriaButton(String name, Pizzeria pizzeria, JFrame frameGenitore, RemoveIngredientsFromPizzeriaPanel removeIngredientsFromPizzeriaPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.frameGenitore = frameGenitore;
        this.removeIngredientsFromPizzeriaPanel = removeIngredientsFromPizzeriaPanel;
        //Aggiungiamo il mouseListener per il pulsante..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                try {
                    //La lista dei nomi degli ingredienti da eliminare
                    ArrayList<String> ingredientsToRemove = new ArrayList<String>();
                    //Per ogni checkBox contenuta nel pannello con gli ingredienti da eliminare...
                    for (JCheckBox checkBox :  removeIngredientsFromPizzeriaPanel.getCheckIngredients()) {
                        if (checkBox.isSelected()) {
                            ingredientsToRemove.add(checkBox.getText());
                        }
                    }
                    //Per ogni ingrediente che vogliamo rimuovere
                    for (String ingredientName : ingredientsToRemove) {
                            pizzeria.getIngredientsManager().removeIngredientFromPizzeria(ingredientName);
                        
                    }
                    frameGenitore.dispose();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (IngredientNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }

        });
    }

}
