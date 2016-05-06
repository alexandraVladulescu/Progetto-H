package data;

import exceptions.ProductNotFoundException;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;

import i_o.FormatType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Markenos
 */
public class Pizzeria {

    private ClientsManager clientManager;
    private ComandeManager comandeManager;
    private IngredientsManager ingredientsManager;

    private Comanda currentComanda;
    private MenuPizze menuPizze;

    public Pizzeria() throws IOException {
        comandeManager = new ComandeManager();
        currentComanda = new Comanda();
        ingredientsManager = IngredientsManager.getInstance();

    }

    public void loadMenuPizza(String path, FormatType type) throws IOException {
        //Carico il Menù delle pizze
        menuPizze = MenuPizze.getInstance();
        menuPizze.loadMenu(path, type);
    }

    public void loadIngredientsMenu(String path, FormatType type) throws IOException {
        this.ingredientsManager.loadMenu(path, type);
    }

    public String printMenuPizze() {
        return "" + this.menuPizze.printAllPizzas() + "\n";
    }

//    public void addComanda(Comanda c) {
//        comandaList.add(c);
//    }
    public MenuPizze getMenuPizze() {
        return menuPizze;
    }

    public ComandeManager getComandeManager() {
        return comandeManager;
    }

    public String printAllComande() {
        return comandeManager.printAllComande();
    }

    public String printAllIngredients() {
        return this.ingredientsManager.printAllIngredient();
    }

    public IngredientsManager getIngredientsManager() {
        return ingredientsManager;
    }

    public CurrentComandaManager getCurrentComandaManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
