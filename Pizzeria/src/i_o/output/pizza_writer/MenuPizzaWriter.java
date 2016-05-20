package i_o.output.pizza_writer;

import data.DescriptionPizza;
import i_o.output.ingredient_writer.*;
import data.Ingredient;
import data.Pizza;
import i_o.FormatType;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Markenos
 */
public class MenuPizzaWriter {
   
    
    
    public MenuPizzaWriter() {
    }
    
    //Scegliamo il writer apposito a seconda che vogliamo lavorare con file Xml o con file di testo...
    //Passiamo come parametri il path del file da scrivere, il tipo di file da scrivere (txt o xml) e la lista delle pizze
    // che vogliamo scrivere...
     public PizzaWriterFactory getFilePizzaWriter(String path,FormatType type, ArrayList<DescriptionPizza> pizzas) throws IOException {
         PizzaWriterFactory filePizzaWriter ;
        switch (type) {
            case TXT :
                filePizzaWriter = new PizzaTxtWriter(path, pizzas);
                break;
            case XML:
                //TO DO!!!
                //fileIngredientWriter = new IngredientXmlWriter(path, ingredients);
                filePizzaWriter = null;    //soluzione temporanea in attesa di implementazione...
                break;
            default : filePizzaWriter = null;
        }
        return filePizzaWriter;
    }
}
