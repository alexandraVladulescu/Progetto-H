/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_V1;

import data.Ingredient;
import data.IngredientsManager;
import data.Pizzeria;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Markenos
 */
public class AddIngredientsPanel extends JPanel implements AddRemoveIngredientsPanel {

    private Pizzeria pizzeria;
    //Teniamo l'ArrayList di checkBox...ci servirà poi in AddRemoveIngredientButton...
    private ArrayList<JCheckBox> checkIngredients;

    AddIngredientsPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.checkIngredients = new ArrayList<JCheckBox>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createIngredientsLine();

    }

    private void createIngredientsLine() {
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
            System.err.println("Errore nella clonazione dell'ingredientManager in AddIngredientsPanel");
        }
    }

    public ArrayList<JCheckBox> getCheckIngredients() {
        return checkIngredients;
    }
    
    

}
