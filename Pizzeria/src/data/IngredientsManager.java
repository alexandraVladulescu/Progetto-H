package data;

import exceptions.IngredientNotFoundException;
import i_o.FormatType;
import i_o.input.ingredient_reader.IngredientReaderFactory;
import i_o.input.ingredient_reader.IngredientTxtReader;
import i_o.input.ingredient_reader.IngredientsListLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
    
    public void loadMenu(String path, FormatType type) throws FileNotFoundException, IOException {
        IngredientsListLoader ingredientsListLoader = new IngredientsListLoader();
        IngredientReaderFactory reader = ingredientsListLoader.getFileIngredientReader(path, type);
        while (reader.hasNextIngredient()) {
            this.addIngredient(reader.getNextIngredient());
            
        }
        Collections.sort(ingredients);
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        IngredientsManager ingredientsManager = new IngredientsManager();
        for(Ingredient ingredient:this.ingredients){
            ingredientsManager.ingredients.add((Ingredient) ingredient.clone());
        }
        return ingredientsManager;
    }
    
    
    
    
}
