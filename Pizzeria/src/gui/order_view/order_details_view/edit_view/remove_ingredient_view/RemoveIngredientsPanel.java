package gui.order_view.order_details_view.edit_view.remove_ingredient_view;

import data.Ingredient;
import data.IngredientsManager;
import data.Pizza;
import data.Pizzeria;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * Pennello contenente gli ingredienti della pizza. Tramite dei checkBox seleziono quelli che voglio eliminare...
 * @author Markenos
 */
public class RemoveIngredientsPanel extends JPanel {

    private Pizzeria pizzeria;
    //Teniamo l'ArrayList di checkBox...ci servirà poi in AddIngredientsButton...
    private ArrayList<JCheckBox> checkIngredients;
    //L'indice della pizza da modificare...
    private int index;
    
    RemoveIngredientsPanel(Pizzeria pizzeria, int index) {
        this.pizzeria = pizzeria;
        this.checkIngredients = new ArrayList<JCheckBox>();
        this.index = index;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createIngredientsLine();

    }

    public void createIngredientsLine() {
        try {
            //Lavoro su una copia della pizza da modificare così da non toccare l'originale che altrimenti
            //subirebbe le modifiche qui fatte
            Pizza pizza = (Pizza) ((Pizza) (this.pizzeria.getCurrentComandaManager().
                                                getCurrentComanda().getPizzasList().get(index))).clone();
            ArrayList<Ingredient> ingredients = pizza.getIngredients();
            ingredients.addAll(pizza.getPlusIngredients());

            //Per ogni ingrediente contenuto nella pizza...
            for (int i = 0; i < ingredients.size(); i++) {
                JCheckBox checkIngredient = new JCheckBox(ingredients.get(i).getName());
                checkIngredients.add(checkIngredient);
                this.add(checkIngredient);
            }
        } catch (CloneNotSupportedException ex) {
            System.err.println("Errore nella clonazione della pizza in RemoveIngredientsPanel");
        }
    }

    public ArrayList<JCheckBox> getCheckIngredients() {
        return checkIngredients;
    }
    
    

}
