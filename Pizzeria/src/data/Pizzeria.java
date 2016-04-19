package data;

import exceptions.ProductNotFoundException;
import exceptions.ComandaNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import i_o.*;
import java.io.IOException;
import java.util.ArrayList;

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
       
    }

    public void setCurrentComanda(Comanda currentComanda) {
        this.currentComanda = currentComanda;
    }

    public void addPizza(String nomePizza) throws PizzaNotFoundInMenuException {
        Pizza p = menuPizze.getPizzaByName(nomePizza);
        if (p != null) {
            currentComanda.addProduct(p);
        }
    }

    public String showComandaDetails() {
        return currentComanda.toString();
    }

    public void loadMenues() throws IOException {
        //Carico il Menù delle pizze
        menuPizze = new MenuPizze();
        menuPizze.loadMenu(new XmlMenuLoader());
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

    public void removePizzaFromComanda(String clientSurname, String nomePizza) throws ComandaNotFoundException, ProductNotFoundException {
        Comanda comandaTrovata = this.comandeManager.searchComandaByName(clientSurname);
        this.setCurrentComanda(comandaTrovata);
        this.removePizza(nomePizza);
    }

    public void addPizzaToComanda(String clientSurname, String nomePizza) throws ComandaNotFoundException, PizzaNotFoundInMenuException {
        Comanda comandaTrovata = this.comandeManager.searchComandaByName(clientSurname);
        this.setCurrentComanda(comandaTrovata);
        this.addPizza(nomePizza);

    }
}
