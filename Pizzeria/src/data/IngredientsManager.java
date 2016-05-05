package data;

import exceptions.AlreadyExistingIngredientException;
import exceptions.IngredientNotFoundException;
import i_o.FormatType;
import i_o.input.ingredient_reader.IngredientReaderFactory;
import i_o.input.ingredient_reader.IngredientsListLoader;
import i_o.output.ingredient_writer.IngredientWriterFactory;
import i_o.output.ingredient_writer.IngredientsListWriter;
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
    
    //Crea un nuovo ingrediente da aggiungere agli ingredienti già presenti nella pizzeria
    public void createNewIngredient(String name, double price) throws  IOException, AlreadyExistingIngredientException{
        Ingredient newIngredient = new Ingredient(name, price);
        //Se l'ingredeinte è già presente nella pizzeria, allora lanciamo un eccezione...
        if (this.ingredients.contains(newIngredient)){
                throw new AlreadyExistingIngredientException("Esiste già l'ingrediente " + newIngredient.getName() + " nella pizzeria.");
        } else{
            this.ingredients.add(newIngredient);
            Collections.sort(ingredients);
            this.writeMenu("./databases/ingredienti.txt", FormatType.TXT);
        }
    }
    
    public String printAllIngredient() {
        String t = "";
        for (Ingredient ingredient : ingredients) {
            t = t + ingredient.toString();
        }
        return t;
    }
    
    public void loadMenu(String path, FormatType type) throws FileNotFoundException, IOException {
         //E' necessario prima di tutto pulire l'arrayList degli ingredienti altrimenti due load consecutivi sdoppierebbero gli elementi..
        this.ingredients.clear();
        IngredientsListLoader ingredientsListLoader = new IngredientsListLoader();
        IngredientReaderFactory reader = ingredientsListLoader.getFileIngredientReader(path, type);
        while (reader.hasNextIngredient()) {
            this.addIngredient(reader.getNextIngredient());
            
        }
        Collections.sort(ingredients);
    }
    
    //Scrive su file gli ingredienti contenuti nella pizzeria
    public void writeMenu (String  path, FormatType type) throws IOException{
        IngredientsListWriter ingredientsListWriter = new IngredientsListWriter();
        IngredientWriterFactory writer = ingredientsListWriter.getFileIngredientWriter(path, type, this.ingredients);
        while(writer.hasNextIngredient()){
            writer.writeNextIngredient();
        }
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
