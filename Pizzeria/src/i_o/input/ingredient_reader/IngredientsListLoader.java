package i_o.input.ingredient_reader;

import i_o.FormatType;
import i_o.input.pizza_reader.PizzaReaderFactory;
import i_o.input.pizza_reader.PizzaXmlReader;
import java.io.FileNotFoundException;

/**
 *
 * @author Markenos
 */
public class IngredientsListLoader {
    public IngredientsListLoader() {
       
    }
    
     public IngredientReaderFactory getFileIngredientReader(String path,FormatType type) throws FileNotFoundException {
         IngredientReaderFactory fileIngredientReader ;
        switch (type) {
            case TXT :
                fileIngredientReader = new IngredientTxtReader(path);
                break;
            case XML:
                fileIngredientReader = new IngredientXmlReader(path);
                break;
            default : fileIngredientReader = null;
        }
        return fileIngredientReader;
    }
     
     
     
}
