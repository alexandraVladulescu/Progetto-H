package gui.order_view.order_details_view.edit_view.remove_ingredient_view;

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
 * Questa classe rappresenta il pulsante "Rimuovi ingredienti" di AddIngredientsPannel che viene usato
 * per confermare che si vogliono rimuovere gli ingredienti selezionati.
 * @author Markenos
 */
public class RemoveIngredientsButton extends JButton {

    private Pizzeria pizzeria;
    //index è l'indice della pizza da modificare...ce lo portiamo dietro da un bel po'...
    private int index;
    //Contiene il pannello contenente i checkbox degli ingredienti da aggiungere o da togliere
    //La scelta del tipo di pannello avviene a runtime passandogli da costruttore il pannello...
    private RemoveIngredientsPanel removeIngredientsPanel;

    RemoveIngredientsButton(String name, Pizzeria pizzeria, int index, RemoveIngredientsPanel removeIngredientsPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.index = index;
        this.removeIngredientsPanel = this.removeIngredientsPanel;

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //La lista dei nomi degli ingredienti da eliminare
                ArrayList<String> ingredientsToRemove = new ArrayList<String>();
                //Per ogni checkBox contenuta nel pannello con gli ingredienti da eliminare...
                for (JCheckBox checkBox : ((RemoveIngredientsPanel) removeIngredientsPanel).getCheckIngredients()) {
                    if (checkBox.isSelected()) {
                        ingredientsToRemove.add(checkBox.getText());
                    }
                }
                //Per ogni ingrediente che vogliamo rimuovere
                for (String ingredientName : ingredientsToRemove) {
                    try {
                        pizzeria.getCurrentComandaManager().removeIngredientToPizza(ingredientName, getIndex());
                        //Utilizzo un metodo setIndex perché sennò java da problemi lavorando in una innerClass...
                        //Perché faccio quello che faccio sotto? Perché l'algoritmo che aggiunge l'ingrediente alla pizza, dopo averlo aggiunto
                        //mette la pizza modificata in fondo alla comanda...quindi il suo index cambia a size()-1...
                        setIndex(pizzeria.getCurrentComandaManager().getCurrentComanda().getPizzasList().size()-1);
                        
                    } catch (ProductNotFoundException ex) {
                        System.err.println("La pizza a cui vuoi rimuovere l'ingrediente non esiste nella comanda corrente");
                    } catch (IngredientNotFoundException ex) {
                        System.err.println("L'ingrediente che vuoi rimuovere non esiste!");
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("Errore durante la clonazione in removeIngredientToPizza");
                    }
                }
                RemoveIngredientFrame.disposeFrame();
                //Senza questa riga di codice il pannello di rimozione ingredienti
                //visualizzava ogni volta gli ingredienti della pizza originale
                removeIngredientsPanel.createIngredientsLine();

            }

        });
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    

}
