package data;

import exceptions.ProductNotFoundException;
import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import i_o.*;
import i_o_V1.FormatType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observer;

/**
 *
 * @author Markenos
 */
public class Pizzeria {

    private ClientsManager clientManager;
    private ComandeManager comandeManager;
    private IngredientsManager ingredientsManager;
    private ProductsManager productsManager;

    private Comanda currentComanda;
    private MenuPizze menuPizze;

    public Pizzeria() throws IOException {
        comandeManager = new ComandeManager();
        currentComanda = new Comanda();
        ingredientsManager = new IngredientsManager();

    }

    public void setCurrentComanda(Comanda currentComanda) {
        this.currentComanda = currentComanda;
    }

    public void setCurrentComanda(String clientSurname) throws ComandaNotFoundException {
        Comanda comandaTrovata = this.comandeManager.searchComandaByName(clientSurname);
        this.setCurrentComanda(comandaTrovata);
    }

    public void addPizza(String nomePizza) throws PizzaNotFoundInMenuException {
        Pizza p = menuPizze.getPizzaByName(nomePizza).copyPizza();
        if (p != null) {

            currentComanda.addProduct(p);
        }
    }

    public String showComandaDetails() {
        return currentComanda.toString();
    }

    public void loadMenuPizza(String path, FormatType type) throws IOException {
        //Carico il Menù delle pizze
        menuPizze = new MenuPizze();
        menuPizze.loadMenu(path, type);
    }

    public void loadIngredientsMenu(String path) throws IOException {
        this.ingredientsManager.loadMenu(path);
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

    public void confirmComanda() {
        comandeManager.addComanda(currentComanda);
    }

    public void setClientToComanda(Client c) {
        this.currentComanda.setClient(c);
    }

    public void removePizza(String nomePizza) throws ProductNotFoundException {
        currentComanda.removeProduct(nomePizza);
    }

    public Comanda getCurrentComanda() {
        return currentComanda;
    }

    public void addIngredientToPizza(String ingredientName, String pizzaName) throws ProductNotFoundException, IngredientNotFoundException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) currentComanda.searchProdcutByName(pizzaName);
        Ingredient ingredient = ingredientsManager.getIngredientByName(ingredientName);
        pizza.addIngredient(ingredient);

        ArrayList<Pizza> temp = (ArrayList<Pizza>) menuPizze.getPizze().clone();// PRELEVO DA DB
        temp.add(pizza);// AGGIUNGO LA MIA PIZZA MODIFICATA
        temp.sort(new ComparatorPizza());
        System.out.println(">>>>>>>>>>>>>>>>>>DENTRO ADD INGREDIENT TO PIZZA\n ");
        System.out.println("\t\t\t\tPROVA DOPO IL SORT : \n" + temp.toString());
        int index = 0;
        for (Pizza p : temp) {
            if (p == (pizza)) {
                break;//schifo
            }
            index++;
        }
        System.out.println(index);
        System.out.println("***********\t\tPIZZA\t" + temp.get(index));
        System.out.println("************PIZZA PIU' SIMILE E'" + temp.get(index - 1));
        System.out.println(">>>>>>>>>>>>>>>>>>FINE \n");

        this.currentComanda.getOrdersList().remove(pizza);//RIMUOVO LA PIZZA CHE AVEVO MODIFICATO
        if (pizza.equals(temp.get(index - 1))) {
            this.currentComanda.addProduct(temp.get(index - 1));//questo ha gia i costi base degli ingred
        }else{
                    
                    }
       //AGGIUNGO QUELLA PIU SIMILE

        //DEVO MODIFICARE LA LISTA DEGLI INGREDIENTI D QUELLA PIU SIMILE
//        temp.get(index-1).getIngredients().;
    }

    public static class ComparatorPizza implements Comparator<Pizza> {

        public ComparatorPizza() {
        }

        @Override
        public int compare(Pizza p1, Pizza p2) {
            ArrayList<Ingredient> tempListIngredient = (ArrayList<Ingredient>) p1.getIngredients().clone();
            ArrayList<Ingredient> p1ListCopy = (ArrayList<Ingredient>) p1.getIngredients().clone();
            ArrayList<Ingredient> p2ListCopy = (ArrayList<Ingredient>) p2.getIngredients().clone();

            p1ListCopy.removeAll(p2ListCopy);// rimuovo dalla lista copiata di p1
            p2ListCopy.removeAll(tempListIngredient); // rimuovo dalla lista copiata di p2
            if (p1ListCopy.size() > p2ListCopy.size()) {
                return 1;
            } else if (p1ListCopy.size() < p2ListCopy.size()) {
                return -1;
            } else {
                if (p1.getPrice() > p2.getPrice()) {
                    return 1;
                } else if (p1.getPrice() < p2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

    }

    public String printAllComande() {
        return comandeManager.printAllComande();
    }

    public String printAllIngredients() {
        return this.ingredientsManager.printAllIngredient();
    }

}
