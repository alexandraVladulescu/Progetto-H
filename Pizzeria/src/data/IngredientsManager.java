package data;

import exceptions.IngredientNotFoundException;
import i_o_V1.IngredientTxtReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class IngredientsManager {
    
    private ArrayList<Ingredient> ingredients;
    
    public IngredientsManager() {
        ingredients = new ArrayList<Ingredient>();
    }
    
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
    
    public String printAllIngredient() {
        String t = "";
        for (Ingredient ingredient : ingredients) {
            t = t + ingredient.toString();
        }
        return t;
    }
    
    public void loadMenu(String path) throws FileNotFoundException, IOException {
        IngredientTxtReader reader = new IngredientTxtReader(path);
        while (reader.hasNextIngredient()) {
            this.addIngredient(reader.getNextIngredient());
            
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    
    public Ingredient getIngredientByName(String ingredientName) throws IngredientNotFoundException {
        Ingredient ingredientTrovato = null;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                ingredientTrovato = ingredient;
                
            }
        }
        if (ingredientTrovato == null) {
            throw new IngredientNotFoundException("Ingrediente \t" + ingredientName + "\t non trovato nel Db");
        } else {
            return ingredientTrovato;
        }
    }
}
