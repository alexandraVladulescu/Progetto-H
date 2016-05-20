package i_o.output.pizza_writer;

import data.DescriptionPizza;
import i_o.output.ingredient_writer.*;
import data.Ingredient;
import data.Pizza;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Questa classe si occupa di scrivere su file di testo le pizze presenti
 * nella pizzeria Viene utilizzata principalmente quando da interfaccia grafica
 * l'utente vuole aggiungere una pizza al menu della pizzeria.
 *
 * @author Markenos
 */
public class PizzaTxtWriter implements PizzaWriterFactory {

    private FileWriter f;
    private PrintWriter printWriter;
    //La lista delle pizze da scrivere sul file
    private ArrayList<DescriptionPizza> pizzas;
    //L'indice della pizza che sta per essere scritta su file
    private int index;

    public PizzaTxtWriter(String path, ArrayList<DescriptionPizza> pizzas) throws IOException {
        f = new FileWriter(path);
        printWriter = new PrintWriter(f);
        this.pizzas = pizzas;
        index = 0;
    }

    @Override
    public void writeNextPizza() {
        printWriter.print(pizzas.get(index).getName() + "\t" + pizzas.get(index).getPrice() + "\n");
        //Per ogni ingrediente presente nella pizza
        for (int i = 0; i < pizzas.get(index).getIngredients().size(); i++){
            //Quando arriviamo all'ultimo ingrediente non dobbiamo stampare la tabulazione finale altrimenti accadono casini poi in fase di lettura
            if (i == (pizzas.get(index).getIngredients().size()-1)){
                printWriter.print(pizzas.get(index).getIngredients().get(i).getName());
            } else{
                printWriter.print(pizzas.get(index).getIngredients().get(i).getName() + "\t");                
            }
        }
        printWriter.print("\n");
        index++;
    }

    @Override
    public boolean hasNextPizza() throws IOException{
        if (index < pizzas.size()){
            return true;
        } else{
            printWriter.close();
            f.close();
            return false;
        }
    }

}
