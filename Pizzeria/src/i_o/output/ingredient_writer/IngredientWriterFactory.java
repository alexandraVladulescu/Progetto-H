package i_o.output.ingredient_writer;

import data.Ingredient;
import java.io.IOException;

/**
 *
 * @author Markenos
 */
public interface IngredientWriterFactory {
    
    abstract void writeNextIngredient();
    abstract boolean hasNextIngredient() throws IOException;
}
