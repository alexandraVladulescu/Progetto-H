package gui.order_view.remove_pizza_view;

import gui.remove_ingredient_view.*;
import data.Ingredient;
import data.IngredientsManager;
import data.MenuPizze;
import data.Pizza;
import data.Pizzeria;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Questo pannello contiene il form per rimuovere una o più pizze dalla pizzeria
 *
 * @author Markenos
 */
public class RemovePizzasFromPizzeriaPanel extends JPanel {

    private Pizzeria pizzeria;
    //Una checkbox per ciascun ingrediente presente nella pizzeria
    ArrayList<JCheckBox> checkPizzas;

    public RemovePizzasFromPizzeriaPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.checkPizzas = new ArrayList<JCheckBox>();
        
        //Usiamo un box Layout con orientamento verticale come LayoutManager
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //Crea una riga per ogni ingrediente nella pizzeria
        this.createPizzasLine();

    }
 
     private void createPizzasLine() {
        try {
            //Lavoro su una copia di MenuPizze così da non modificare l'originale che sennò subirebbe le modifiche fatte qui
            MenuPizze tempMenuPizze = (MenuPizze) pizzeria.getMenuPizze().clone();
            ArrayList<Pizza> pizzas = tempMenuPizze.getPizze();

            //Per ogni ingrediente contenuto nella pizzeria...
            for (int i = 0; i < pizzas.size(); i++) {
                JCheckBox checkPizza = new JCheckBox(pizzas.get(i).getName());
                checkPizzas.add(checkPizza);
                this.add(checkPizza);
            }
        } catch (CloneNotSupportedException ex) {
            System.err.println("Errore nella clonazione di MenuPizze in RemovePizzasFromPizzeriaPanel");
        }
    }

    public ArrayList<JCheckBox> getCheckPizzas() {
        return checkPizzas;
    }
    
    
    
}
