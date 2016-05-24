package gui.order_view.remove_pizza_view;


import data.DescriptionPizza;
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
         MenuPizze tempMenuPizze = MenuPizze.getInstance();
         ArrayList<DescriptionPizza> pizzas = tempMenuPizze.getPizze();
         for (int i = 0; i < pizzas.size(); i++) {
             JCheckBox checkPizza = new JCheckBox(pizzas.get(i).getName());
             checkPizzas.add(checkPizza);
             this.add(checkPizza);
         }
    }

    public ArrayList<JCheckBox> getCheckPizzas() {
        return checkPizzas;
    }
    
    
    
}
