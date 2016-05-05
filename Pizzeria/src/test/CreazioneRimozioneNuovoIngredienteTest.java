package test;

import data.Pizzeria;
import exceptions.AlreadyExistingIngredientException;
import exceptions.IngredientNotFoundException;
import i_o.FormatType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testa la funzionalità del MODELLO per quanto riguarda la creazione di un
 * nuovo ingrediente da aggiungere alla pizzeria.
 *
 * @author Markenos
 */
public class CreazioneRimozioneNuovoIngredienteTest {

    public static void main(String[] args) throws IOException {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);

        //Stampo gli ingredienti correnti nella pizzeria
        System.out.println(pizzeria.printAllIngredients());

        try {
            //Aggiungo un nuovo ingrediente
            pizzeria.getIngredientsManager().createNewIngredient("Marco", 2.0);
            pizzeria.getIngredientsManager().removeIngredientFromPizzeria("marco");

        } catch (AlreadyExistingIngredientException ex) {
            System.out.println(ex.getMessage());
        } catch (IngredientNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        //Stampo un separatore
        System.out.println("---------------------------------------------------");

        //Ricarico gli ingredienti
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);

        //Ristampo gli ingredienti correnti nella pizzeria
        System.out.println(pizzeria.printAllIngredients());
    }
}
