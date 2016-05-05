package i_o.output.ingredient_writer;

import data.Ingredient;
import i_o.FormatType;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Markenos
 */
public class IngredientsListWriter {
   
    
    
    public IngredientsListWriter() {
    }
    
    //Scegliamo il writer apposito a seconda che vogliamo lavorare con file Xml o con file di testo...
    //Passiamo come parametri il path del file da scrivere, il tipo di file da scrivere (txt o xml) e la lista degli
    //ingredienti che vogliamo scrivere...
     public IngredientWriterFactory getFileIngredientWriter(String path,FormatType type, ArrayList<Ingredient> ingredients) throws IOException {
         IngredientWriterFactory fileIngredientWriter ;
        switch (type) {
            case TXT :
                fileIngredientWriter = new IngredientTxtWriter(path, ingredients);
                break;
            case XML:
                //TO DO!!!
                //fileIngredientWriter = new IngredientXmlWriter(path, ingredients);
                fileIngredientWriter = null;    //soluzione temporanea in attesa di implementazione...
                break;
            default : fileIngredientWriter = null;
        }
        return fileIngredientWriter;
    }
}
