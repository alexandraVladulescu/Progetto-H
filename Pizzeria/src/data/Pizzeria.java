package data;

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
        
        loadMenus();
        currentComanda = new Comanda();
        comandaList = new ArrayList<>();
    }
    
    public void createComanda(Comanda c){
        setCurrentComanda(c);
        comandaList.add(c);
    }

    public void setCurrentComanda(Comanda currentComanda) {
        this.currentComanda = currentComanda;
    }
    
    public void addPizza(String nome) throws PizzaNotFoundInMenuException{
        Pizza p=menuPizze.getPizzaByName(nome);
        if(p!=null){
            currentComanda.addProduct(p);
        }
    }

    public Comanda getCurrentComanda() {
        return currentComanda;
    }
    
    private void loadMenus() throws IOException{
        //Carico il Menù delle pizze
        menuPizze = new MenuPizze();
        menuPizze.loadMenu();
    }
    
}
