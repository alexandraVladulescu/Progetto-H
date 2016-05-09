package gui.order_view.order_details_view.edit_view.add_ingredient_view;

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
 * Questa classe rappresenta il pulsante "Aggiungi ingredienti" di AddIngredientsPannel che viene usato
 * per confermare che si vogliono aggiungere gli ingredienti selezionati.
 * @author Markenos
 */
public class AddIngredientsButton extends JButton {

    private Pizzeria pizzeria;
    //index è l'indice della pizza da modificare...ce lo portiamo dietro da un bel po'...
    private int index;
    //Perchè portarci dietro il frame genitore? Così sappiamo come chiuderlo...
    private JFrame frameGenitore;
    //Contiene il pannello contenente i checkbox degli ingredienti da aggiungere o da togliere
    //La scelta del tipo di pannello avviene a runtime passandogli da costruttore il pannello...
    private AddIngredientsPanel addIngredientsPanel;

    AddIngredientsButton(String name, Pizzeria pizzeria, int index, JFrame frameGenitore, AddIngredientsPanel addIngredientsPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.index = index;
        this.frameGenitore = frameGenitore;
        this.addIngredientsPanel = addIngredientsPanel;

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //La lista dei nomi degli ingredienti da aggiungere
                ArrayList<String> ingredientsToAdd = new ArrayList<String>();
                //Per ogni checkBox contenuta nel pannello con gli ingredienti da aggiungere...
                for (JCheckBox checkBox : ((AddIngredientsPanel) addIngredientsPanel).getCheckIngredients()) {
                    if (checkBox.isSelected()) {
                        ingredientsToAdd.add(checkBox.getText());
                    }
                }
                //Per ogni ingrediente che vogliamo aggiungere
                for (String ingredientName : ingredientsToAdd) {
                    try {
                        pizzeria.getCurrentComandaManager().addIngredientToPizza(ingredientName, getIndex());
                        //Utilizzo un metodo setIndex perché sennò java da problemi lavorando in una innerClass...
                        //Perché faccio quello che faccio sotto? Perché l'algoritmo che aggiunge l'ingrediente alla pizza, dopo averlo aggiunto
                        //mette la pizza modificata in fondo alla comanda...quindi il suo index cambia a size()-1...
                        setIndex(pizzeria.getCurrentComandaManager().getCurrentComanda().getOrdersList().size()-1);
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

        });
    }

    private void setIndex(int index){
        this.index  = index;
    }

    public int getIndex() {
        return index;
    }
    
    
    
}
