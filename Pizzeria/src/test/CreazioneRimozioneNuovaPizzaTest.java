package test;

import data.Ingredient;
import data.Pizzeria;
import exceptions.AlreadyExistingIngredientException;
import exceptions.AlreadyExistingPizzaException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import i_o.FormatType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testa la funzionalità del MODELLO per quanto riguarda la creazione di una
 * nuova pizza da aggiungere alla pizzeria.
 *
 * @author Markenos
 */
public class CreazioneRimozioneNuovaPizzaTest {

    public static void main(String[] args) throws IOException, IngredientNotFoundException {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.loadIngredientsMenu("./databases/ingredienti.txt", FormatType.TXT);
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);

        //Stampo le pizze correnti nella pizzeria
        System.out.println(pizzeria.printMenuPizze());

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(pizzeria.getIngredientsManager().getIngredientByName("Pomodoro"));
        ingredients.add(pizzeria.getIngredientsManager().getIngredientByName("Mozzarella"));
        try {
            pizzeria.getMenuPizze().createNewPizza("Robutti", 5.0, ingredients);
            pizzeria.getMenuPizze().removePizzaFromPizzeria("Robutti");
        } catch (AlreadyExistingPizzaException ex) {
            System.out.println(ex.getMessage());
        } catch (PizzaNotFoundInMenuException ex){
            System.out.println(ex.getMessage());
        }

        //Stampo un separatore
        System.out.println("---------------------------------------------------");

        //Ricarico le pizze da file
        pizzeria.loadMenuPizza("./databases/pizze.txt", FormatType.TXT);
        //Ristampo le pizze correnti nella pizzeria
        System.out.println(pizzeria.printMenuPizze());
    }
}
