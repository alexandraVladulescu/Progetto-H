package gui_V1;

import data.Pizzeria;
import exceptions.IngredientNotFoundException;
import exceptions.ProductNotFoundException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 * Questa classe rappresenta
 *
 * @author Markenos
 */
public class AddRemoveIngredientButton extends JButton {

    private Pizzeria pizzeria;
    //index è l'indice della pizza da modificare...ce lo portiamo dietro da un bel po'...
    private int index;
    //Perchè portarci dietro il frame genitore? Così sappiamo come chiuderlo...
    private JFrame frameGenitore;
    //Contiene il pannello contenente i checkbox degli ingredienti da aggiungere o da togliere
    //La scelta del tipo di pannello avviene a runtime passandogli da costruttore il pannello...
    private AddRemoveIngredientsPanel addRemoveIngredientPanel;

    AddRemoveIngredientButton(String name, Pizzeria pizzeria, int index, JFrame frameGenitore, AddRemoveIngredientsPanel addRemoveIngredientPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.index = index;
        this.frameGenitore = frameGenitore;
        this.addRemoveIngredientPanel = addRemoveIngredientPanel;

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Se il pannello passato è quello contenente la lista degli ingredienti da aggiungere
                if (addRemoveIngredientPanel instanceof AddIngredientsPanel) {
                    //La lista dei nomi degli ingredienti da aggiungere
                    ArrayList<String> ingredientsToAdd = new ArrayList<String>();
                    //Per ogni checkBox contenuta nel pannello con gli ingredienti da aggiungere...
                    for (JCheckBox checkBox : ((AddIngredientsPanel)addRemoveIngredientPanel).getCheckIngredients()) {
                        if (checkBox.isSelected()){
                            ingredientsToAdd.add(checkBox.getText());
                        }
                    }
                    //Per ogni ingrediente che vogliamo aggiungere
                    for (String ingredientName:ingredientsToAdd){
                        try {
                            pizzeria.addIngredientToPizza(ingredientName, index);
                        } catch (ProductNotFoundException ex) {
                            System.err.println("La pizza a cui vuoi aggiungere l'ingrediente non esiste nella comanda corrente");
                        } catch (IngredientNotFoundException ex) {
                            System.err.println("L'ingrediente che vuoi aggiungere non esiste!");
                        } catch (CloneNotSupportedException ex) {
                            System.err.println("Errore durante la clonazione in addIngredientToPizza");
                        }
                    }
                    frameGenitore.dispose();
                }
            }

        });
    }

}
