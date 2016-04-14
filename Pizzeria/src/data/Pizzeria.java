package data;

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

    public Pizzeria() {
        
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
    
    public void addPizza(String nome){
        Pizza p=menuPizze.getPizzaByName(nome);
        if(p!=null){
            currentComanda.addProduct(p);
        }
    }

    public Comanda getCurrentComanda() {
        return currentComanda;
    }
    
    private void loadMenus(){
        //Carico il Menù delle pizze
        menuPizze = new MenuPizze();
        menuPizze.loadMenu();
    }
    
}
