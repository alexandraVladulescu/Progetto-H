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

    public void addIngredientToPizza(String ingredientName, int index) throws ProductNotFoundException, IngredientNotFoundException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) currentComanda.searchProductByIndex(index);
        Ingredient ingredient = ingredientsManager.getIngredientByName(ingredientName);//se viene trovato viene resituito l'ingrediente che vogliamo aggiungere
        pizza.addPlusIngredient(ingredient);//Add ingrediente all'istanza della pizza in currentComanda

        ArrayList<Pizza> temp = (ArrayList<Pizza>) menuPizze.getPizze().clone();// PRELEVO DA DB E CLONO IL MENU
        temp.add(pizza);// AGGIUNGO LA MIA PIZZA MODIFICATA
        temp.sort(new Pizza.ComparatorPizza());//  USO IL COMPARATOR E RIORDINO IL MIO INSIEME (HO ALL'INTERNO ANCHE LA MIA PIZZA MODIFICATA)
        System.out.println(">>>>>>>>>>>>>>>>>>DENTRO ADD INGREDIENT TO PIZZA\n ");
        System.out.println("\t\t\t\tPROVA DOPO IL SORT : \n" + temp.toString());
        int k = 0;
        for (Pizza p : temp) {
            if (p == (pizza)) {
                break;//schifo -->switch to WHILE!!
            }
            k++;
        }
        System.out.println("\tPosizione nella lista temp ->" + k);// k mi dice in che posizione è la mia pizza nella lista che ho ordinato
        System.out.println("***********\t\t\t LA MIA PIZZA \t" + temp.get(k));
        System.out.println("************PIZZA PIU' SIMILE E'" + temp.get(k - 1));
        System.out.println(">>>>>>>>>>>>>>>>>>FINE \n");

        this.currentComanda.getOrdersList().remove(pizza);//RIMUOVO LA PIZZA CHE AVEVO MODIFICATO DALLA CURRENTCOMANDA
        // DEVO CONTROLLARE CHE LA POSIZIONE IN CUI SI E' CLASSIFICATA LA MIA PIZZA MODIFICATA NON SIA LA PRIMA (OVVERO K == 0)
        if (k == 0) {// SE E' LA PRIMA RIAGGIUNGO LA MIA PIZZA (FORSE CASO IMPOSSIBILE)
            this.currentComanda.addProduct(pizza);
        } else if (pizza.equals(temp.get(k - 1))) {
            this.currentComanda.addProduct(temp.get(k - 1));//questo ha gia i costi base degli ingred

        } else {
            int j = 0;
            boolean exit = false;
            while (!exit) {

                ArrayList<Ingredient> candidato = (ArrayList<Ingredient>) temp.get(k - 1 - j).getIngredients().clone();
                ArrayList<Ingredient> modificata = (ArrayList<Ingredient>) pizza.getIngredients().clone();
                ArrayList<Ingredient> modificataPlus = (ArrayList<Ingredient>) pizza.getPlusIngredients().clone();
                modificata.addAll(modificataPlus);
                candidato.removeAll(modificata); //mi serve se il primo if non è verificato

                if (candidato.isEmpty()) {
                    // se candidato è vuoto -> gli ingredienti base vanno bene devo solo aggiungere gli altri plus
                    modificata.removeAll(temp.get(k - 1 - j).getIngredients());
                    temp.get(k - 1 - j).getPlusIngredients().addAll(modificata);
                    this.currentComanda.addProduct(temp.get(k - j - 1));
                    exit = true;
                }
                j++;
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
