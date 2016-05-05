package i_o.output.pizza_writer;

import i_o.output.ingredient_writer.*;
import data.Ingredient;
import java.io.IOException;

/**
 *
 * @author Markenos
 */
public interface PizzaWriterFactory {
    
    abstract void writeNextPizza();
    abstract boolean hasNextPizza() throws IOException;
}
