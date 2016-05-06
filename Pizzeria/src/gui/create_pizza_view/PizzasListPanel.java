package gui.create_pizza_view;

import data.Ingredient;
import data.IngredientsManager;
import gui.create_ingredient_view.*;
import data.Pizzeria;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questo pannello contiene l'elenco degli ingredienti della pizzeria
 * Tramite dei checkbox si possono scegliere quelli da aggiungere alla nuova pizza
 * @author Markenos
 */
public class PizzasListPanel extends JPanel {

    private Pizzeria pizzeria;
    //Una checkbox per ciascun ingrediente presente nella pizzeria
    ArrayList<JCheckBox> checkIngredients;

    public PizzasListPanel( Pizzeria pizzeria) {

        this.pizzeria = pizzeria;
        this.checkIngredients = new ArrayList<JCheckBox>();
        
        //Usiamo un box Layout con orientamento verticale come LayoutManager
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //Crea una riga per ogni ingrediente nella pizzeria
        this.createPizzasLine();

    }

    private void createPizzasLine() {
        try {
            //Lavoro su una copia di IngredientManager così da non toccare l'originale che altrimenti subirebbe le modifiche qui fatte
            IngredientsManager tempIngredientsManager = (IngredientsManager) pizzeria.getIngredientsManager().clone();
            ArrayList<Ingredient> ingredients = tempIngredientsManager.getIngredients();

            //Per ogni ingrediente contenuto nella pizzeria...
            for (int i = 0; i < ingredients.size(); i++) {
                JCheckBox checkIngredient = new JCheckBox(ingredients.get(i).getName());
                checkIngredients.add(checkIngredient);
                this.add(checkIngredient);
            }
        } catch (CloneNotSupportedException ex) {
            System.err.println("Errore nella clonazione dell'ingredientManager in RemoveIngredientsFromPizzeriaPanel");
        }
    }
   
     public ArrayList<JCheckBox> getCheckIngredients() {
        return checkIngredients;
    }
    
}
