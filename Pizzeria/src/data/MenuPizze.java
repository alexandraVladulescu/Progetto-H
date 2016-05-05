/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.AlreadyExistingPizzaException;
import exceptions.PizzaNotFoundInMenuException;
import i_o.FormatType;
import i_o.input.pizza_reader.MenuPizzaLoader;
import i_o.input.pizza_reader.PizzaReaderFactory;
import i_o.output.pizza_writer.MenuPizzaWriter;
import i_o.output.pizza_writer.PizzaWriterFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author User
 */
public class MenuPizze implements Cloneable {

    //AcquireMenu acquireMenu;
    // private MenuPizzeReader menuReader;
    private ArrayList<Pizza> pizze;

    public MenuPizze() {

        pizze = new ArrayList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MenuPizze mP = new MenuPizze();
        for (Pizza p : pizze) {
            mP.pizze.add((Pizza) p.clone());
        }
        return mP;

    }
// ./databases/pizze.txt
    //./databases/MenuPizze.xml

    public void loadMenu(String path, FormatType type) throws IOException { //MI ARRIVANO COME PRODUCT, FACCIO IL CAST
        //E' necessario fare il clear della collection altrimenti
        //due caricamenti consecutivi possono sdoppiare le pizze
        pizze.clear();
        MenuPizzaLoader menuPizzaLoader = new MenuPizzaLoader();
        PizzaReaderFactory reader = menuPizzaLoader.getFilePizzaReader(path, type);
        while (reader.hasNextProduct()) {
            pizze.add(reader.getNextProduct());
        }
        Collections.sort(pizze);

    }

    public void writeMenu(String path, FormatType type) throws IOException{
        MenuPizzaWriter menuPizzaWriter = new MenuPizzaWriter();
        PizzaWriterFactory writer = menuPizzaWriter.getFilePizzaWriter(path, type, pizze);
        while(writer.hasNextPizza()){
            writer.writeNextPizza();
        }
    }
    
    //Metodo per aggiungere una pizza all'elenco delle pizze presenti nella pizzeria.
    public void createNewPizza(String name, double price, ArrayList<Ingredient> ingredients) throws AlreadyExistingPizzaException, IOException{
        //Effettuiamo un controllo: se esiste già una pizza con questo nome lanciamo un eccezione
        //in quanto non possono esistere due pizze aventi lo stesso nome
        for(Pizza p: pizze){
            if (name.equalsIgnoreCase(p.getName())){
                throw new AlreadyExistingPizzaException("Esiste già una pizza con il nome " + name);
            }
        }
        //Creiamo la pizza da inserire nel file
        Pizza pizza = new Pizza(name, price);
        for(Ingredient ingredient : ingredients){
            pizza.addIngredient(ingredient);
        }
        //Aggiungiamo la pizza alle altre
        pizze.add(pizza);
        //Riordiniamo la collection
        Collections.sort(pizze);
        //Scriviamo su file le modifiche
        this.writeMenu("./databases/pizze.txt", FormatType.TXT);
    }
    
    public ArrayList<Pizza> printAllPizzas() {
        return pizze;
    }

    public Pizza getPizzaByName(String name) throws PizzaNotFoundInMenuException {
        Pizza p = null;
        for (Pizza pizza : pizze) {
            if (pizza.getName().equalsIgnoreCase(name)) {
                //return pizza;
                p = pizza;
            }
        }
        if (p == null) {
            throw new PizzaNotFoundInMenuException("PIZZA NOT FOUND EXCEPTION ; PIZZA\t" + name + "\n");
        }
        return p;
    }

    public int getMenuSize() {
        return pizze.size();
    }

    public ArrayList<Pizza> getPizze() {
        return pizze;
    }

}
