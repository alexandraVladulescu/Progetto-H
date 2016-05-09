package gui.order_view.order_details_view.edit_view.add_ingredient_view;

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
 * Pannello contenente tutti gli ingredienti della pizzeria. Sono presenti dei checkBox uno per ingrediente
 * in modo che si possono selezionare quelli da aggiungere alla pizza da modificare
 * @author Markenos
 */
public class AddIngredientsPanel extends JPanel {

    private Pizzeria pizzeria; 
    //index è l'indice della pizza da modificare...ce lo portiamo dietro da un bel po'...
    private int index;
    //Teniamo l'ArrayList di checkBox...ci servirà poi in AddIngredientsButton...
    private ArrayList<JCheckBox> checkIngredients;

    AddIngredientsPanel(Pizzeria pizzeria, int index) {
        this.pizzeria = pizzeria;
        this.index = index;
        this.checkIngredients = new ArrayList<JCheckBox>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createIngredientsLine();

    }

    private void createIngredientsLine() {
        try {
            //Lavoro su una copia di IngredientManager e della pizza da modificare così da non toccare l'originale che altrimenti subirebbe le modifiche qui fatte
            IngredientsManager tempIngredientsManager = (IngredientsManager) pizzeria.getIngredientsManager().clone();
            ArrayList<Ingredient> ingredients = tempIngredientsManager.getIngredients();
            Pizza pizza = (Pizza) ((Pizza)(pizzeria.getCurrentComandaManager().getCurrentComanda().getPizzasList().get(index))).clone();
            ArrayList<Ingredient> currentPizzaIngredients = new ArrayList<Ingredient>();
            currentPizzaIngredients = pizza.getIngredients();
            currentPizzaIngredients.addAll(pizza.getPlusIngredients());
            ingredients.removeAll(currentPizzaIngredients);

            //Per ogni ingrediente contenuto nella pizzeria...
            for (int i = 0; i < ingredients.size(); i++) {
                JCheckBox checkIngredient = new JCheckBox(ingredients.get(i).getName());
                checkIngredients.add(checkIngredient);
                this.add(checkIngredient);
            }
        } catch (CloneNotSupportedException ex) {
            System.err.println("Errore nella clonazione dell'ingredientManager in AddIngredientsPanel");
        }
    }

    public ArrayList<JCheckBox> getCheckIngredients() {
        return checkIngredients;
    }
    
    

}
