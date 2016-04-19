package data;

import i_o.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Markenos
 */
public class Pizzeria {

    ClientsManager clientManager;
    ComandeManager comandeManager;
    ProductsManager productsManager;

    private ArrayList<Comanda> comandaList;
    private Comanda currentComanda;
    private MenuPizze menuPizze;

    public Pizzeria() throws IOException {
        comandeManager = new ComandeManager();
        currentComanda = new Comanda();
        comandaList = new ArrayList<>();
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
        menuPizze.loadMenu(new AcquireXmlMenu());
    }

    public String getMenuPizze() {
        return "" + this.menuPizze.printAllPizzas() + "\n";
    }

    public void addComanda(Comanda c) {
        comandaList.add(c);
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
