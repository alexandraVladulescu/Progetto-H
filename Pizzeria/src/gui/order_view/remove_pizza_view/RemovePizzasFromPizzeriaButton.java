package gui.order_view.remove_pizza_view;

import data.Pizzeria;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author Markenos
 */
public class RemovePizzasFromPizzeriaButton extends JButton {

    private Pizzeria pizzeria;
//Il pannello contenente il form da cui preleviamo i dati da inserire per il nuovo ingrediente
    private RemovePizzasFromPizzeriaPanel removePizzassFromPizzeriaPanel;

    public RemovePizzasFromPizzeriaButton(String name, Pizzeria pizzeria, RemovePizzasFromPizzeriaPanel removePizzasFromPizzeriaPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.removePizzassFromPizzeriaPanel = removePizzassFromPizzeriaPanel;
        //Aggiungiamo il mouseListener per il pulsante..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                try {
                    //La lista dei nomi delle pizze da eliminare
                    ArrayList<String> pizzasToRemove = new ArrayList<String>();
                    //Per ogni checkBox contenuta nel pannello con gli ingredienti da eliminare...
                    for (JCheckBox checkBox :  removePizzasFromPizzeriaPanel.getCheckPizzas()) {
                        if (checkBox.isSelected()) {
                            pizzasToRemove.add(checkBox.getText());
                        }
                    }
                    //Per ogni ingrediente che vogliamo rimuovere
                    for (String pizzaName : pizzasToRemove) {
                            pizzeria.getMenuPizze().removePizzaFromPizzeria(pizzaName);
                        
                    }
                    RemovePizzasFromPizzeriaFrame.disposeFrame();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                } catch (PizzaNotFoundInMenuException ex) {
                    System.err.println(ex.getMessage());
                }
            }

        });
    }

}
