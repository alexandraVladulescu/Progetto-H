package i_o.output.ingredient_writer;

import data.Ingredient;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Questa classe si occupa di scrivere su file di testo gli ingredienti presenti
 * nella pizzeria Viene utilizzata principalmente quando da interfaccia grafica
 * l'utente vuole aggiungere un ingrediente alla pizzeria.
 *
 * @author Markenos
 */
public class IngredientTxtWriter implements IngredientWriterFactory {

    private FileWriter f;
    private PrintWriter printWriter;
    //La lista degli ingredienti da scrivere sul file
    private ArrayList<Ingredient> ingredients;
    //L'indice dell'ingrediente che sta per essere scritto su file
    private int index;

    public IngredientTxtWriter(String path, ArrayList<Ingredient> ingredients) throws IOException {
        f = new FileWriter(path);
        printWriter = new PrintWriter(f);
        this.ingredients = ingredients;
        index = 0;
    }

    @Override
    public void writeNextIngredient() {
        printWriter.println(ingredients.get(index).getName() + "\t" + ingredients.get(index).getPrice());
        index++;
    }

    @Override
    public boolean hasNextIngredient() throws IOException{
        if (index < ingredients.size()){
            return true;
        } else{
            printWriter.close();
            f.close();
            return false;
        }
    }

}
