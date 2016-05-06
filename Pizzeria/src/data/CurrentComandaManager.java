/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.ComandaNotFoundException;
import exceptions.IngredientNotFoundException;
import exceptions.PizzaNotFoundInMenuException;
import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Francesco
 */
public class CurrentComandaManager extends Observable {

    private Comanda currentComanda;
    private ComandeManager comandeManager;
    private MenuPizze menuPizze;// diventato Singleton
    private IngredientsManager ingredientsManager;

    public CurrentComandaManager() {
        comandeManager = new ComandeManager();
        currentComanda = new Comanda();
        menuPizze = MenuPizze.getInstance();// setto il menuPizze con l'unica istanza che esiste
        ingredientsManager = IngredientsManager.getInstance();// setto il menu degli ingredienti con l'unica istanza
    }

    public Comanda createComanda() {
        currentComanda = new Comanda();
        return currentComanda;// Crea la comanda e la restituisce al chiamante. NB: non viene aggiunta all'arrayList
    }

    public void setCurrentComanda(Comanda currentComanda) {// Setta la comanda con parametro oggetto comanda
        this.currentComanda = currentComanda;
    }

    public void setCurrentComanda(String clientSurname) throws ComandaNotFoundException {// Setta la comanda da String,
        Comanda comandaTrovata = this.comandeManager.searchComandaByName(clientSurname);//Controlla se esiste e se la fa restituire
        this.setCurrentComanda(comandaTrovata);//Usa il metodo setCurrentComanda con parametro oggetto
    }

    public Comanda getCurrentComanda() {
        return currentComanda;
    }

    public void confirmComanda() {
        this.comandeManager.addComanda(currentComanda);
        setChanged();
        notifyObservers();
    }

    public void setClientToComanda(Client c) {
        this.currentComanda.setClient(c);
    }

    public void addPizza(String nomePizza) throws PizzaNotFoundInMenuException, CloneNotSupportedException {
        Pizza p = (Pizza) menuPizze.getPizzaByName(nomePizza).clone();
        if (p != null) {

            this.currentComanda.addProduct(p);
            setChanged();
            notifyObservers();
        }

    }

    public void addPizza(Pizza p) {
        this.currentComanda.addProduct(p);
        setChanged();
        notifyObservers();
    }

    public String showComandaDetails() {
        return this.currentComanda.toString();
    }

    public void removePizza(String nomePizza) throws ProductNotFoundException {
        this.currentComanda.removeProduct(nomePizza);
    }

    public void removePizza(int index) throws ProductNotFoundException {
        this.currentComanda.removeProduct(index);
        setChanged();
        notifyObservers();
    }

    public void addIngredientToPizza(String ingredientName, int index) throws ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) this.currentComanda.searchProductByIndex(index);
        Ingredient ingredient = (Ingredient) ingredientsManager.getIngredientByName(ingredientName).clone();//se viene trovato viene resituito l'ingrediente che vogliamo aggiungere
        pizza.addPlusIngredient(ingredient);//Add ingrediente all'istanza della pizza in currentComanda
        // System.out.println("\t\t\t\t 1 MENU PIZZE\n" + printMenuPizze());
        MenuPizze tempMenu = (MenuPizze) menuPizze.clone();// PRELEVO DA DB E CLONO IL MENU
        ArrayList<Pizza> temp = tempMenu.getPizze();
        temp.add(pizza);// AGGIUNGO LA MIA PIZZA MODIFICATA
        temp.sort(new Pizza.ComparatorPizza());//  USO IL COMPARATOR E RIORDINO IL MIO INSIEME (HO ALL'INTERNO ANCHE LA MIA PIZZA MODIFICATA)
        // System.out.println(">>>>>>>>>>>>>>>>>>DENTRO ADD INGREDIENT TO PIZZA\n ");
        // System.out.println("\t\t\t\tPROVA DOPO IL SORT : \n" + temp.toString());
        int k = 0;
        for (Pizza p : temp) {
            if (p == (pizza)) {
                break;//schifo -->switch to WHILE!!
            }
            k++;
        }
        // System.out.println("\tPosizione nella lista temp ->" + k);// k mi dice in che posizione è la mia pizza nella lista che ho ordinato
        // System.out.println("***********\t\t\t LA MIA PIZZA \t" + temp.get(k));
        // System.out.println("************PIZZA PIU' SIMILE E'" + temp.get(k - 1));
        // System.out.println(">>>>>>>>>>>>>>>>>>FINE \n");
        // System.out.println("\t\t\t\t 2 MENU PIZZE\n" + printMenuPizze());
        this.currentComanda.getOrdersList().remove(pizza);
        //  System.out.println("\t\t\t\t 3 MENU PIZZE\n" + printMenuPizze());//RIMUOVO LA PIZZA CHE AVEVO MODIFICATO DALLA CURRENTCOMANDA
        // DEVO CONTROLLARE CHE LA POSIZIONE IN CUI SI E' CLASSIFICATA LA MIA PIZZA MODIFICATA NON SIA LA PRIMA (OVVERO K == 0)
        if (k == 0) {// SE E' LA PRIMA RIAGGIUNGO LA MIA PIZZA (FORSE CASO IMPOSSIBILE)
            this.currentComanda.addProduct(pizza);
        } else if (pizza.equals(temp.get(k - 1))) {
            this.currentComanda.addProduct(temp.get(k - 1));//questo ha gia i costi base degli ingred

        } else {
            int j = 0;
            boolean exit = false;
            while (!exit) {
                //    System.out.println("\t\t\t\t 4 MENU PIZZE\n" + printMenuPizze());
                //           ATTENZIONE I CLONE QUI SOTTO SON DA RISISTEMARE !!!!!
                ArrayList<Ingredient> candidato = (ArrayList<Ingredient>) temp.get(k - 1 - j).getIngredients().clone();
                ArrayList<Ingredient> modificata = (ArrayList<Ingredient>) pizza.getIngredients().clone();
                ArrayList<Ingredient> modificataPlus = (ArrayList<Ingredient>) pizza.getPlusIngredients().clone();
                modificata.addAll(modificataPlus);
                // System.out.println("STAMPO MODIFICATA ********* "+ modificata.toString());
                candidato.removeAll(modificata); //mi serve se il primo if non è verificato
                //   System.out.println("\t\t\t\t 5 MENU PIZZE\n" + printMenuPizze());
                // System.out.println("STAMPO CANDIDATO *****"+candidato.toString());
                if (candidato.isEmpty()) {
                    //  System.out.println("\t\t\t\t 6 MENU PIZZE\n" + printMenuPizze());
                    // System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n");
                    // se candidato è vuoto -> gli ingredienti base vanno bene devo solo aggiungere gli altri plus
                    // System.out.println("MODIFICATA DENTRO IF 1"+modificata.toString());
                    modificata.removeAll(temp.get(k - 1 - j).getIngredients());
                    //   System.out.println("MODIFICATA DENTRO L IF 2 "+modificata.toString());
                    // System.out.println("\t\t\t\t 7 MENU PIZZE\n" + printMenuPizze());
                    temp.get(k - 1 - j).getPlusIngredients().addAll(modificata);
                    //   System.out.println("\t\t\t\t 8 MENU PIZZE\n" + printMenuPizze());
                    this.currentComanda.addProduct(temp.get(k - j - 1));
                    exit = true;
                }
                j++;
            }
        }

    }

    public void removeIngredientToPizza(String ingredientName, int index) throws ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) this.currentComanda.searchProductByIndex(index);
        Ingredient ingredient = (Ingredient) ingredientsManager.getIngredientByName(ingredientName).clone();//se viene trovato viene resituito l'ingrediente che vogliamo aggiungere
        if (!pizza.removePlusIngredient(ingredient)) { // PROVO A VEDERE SE RIMUOVER DA PLUS
            if (!pizza.removeIngredient(ingredient)) {// SE NON E' IN PLUS LO RIMUOVO DAGLI ING BASE
                // SE L'INGREDIENTE NON E' IN NESSUNO DEI DUE BISOGNA LANCIARE EXCEPTION !
                //PERCHE' VUOL DIRE CHE STO TOGLIENDO UN INGREDIENTE CHE NON C'E' IN QUELLA PIZZA
            }
        }
        // pizza.addPlusIngredient(ingredient);//Add ingrediente all'istanza della pizza in currentComanda
        // System.out.println("\t\t\t\t 1 MENU PIZZE\n" + printMenuPizze());
        MenuPizze tempMenu = (MenuPizze) menuPizze.clone();// PRELEVO DA DB E CLONO IL MENU
        ArrayList<Pizza> temp = tempMenu.getPizze();
        temp.add(pizza);// AGGIUNGO LA MIA PIZZA MODIFICATA
        temp.sort(new Pizza.ComparatorPizza());//  USO IL COMPARATOR E RIORDINO IL MIO INSIEME (HO ALL'INTERNO ANCHE LA MIA PIZZA MODIFICATA)
        // System.out.println(">>>>>>>>>>>>>>>>>>DENTRO ADD INGREDIENT TO PIZZA\n ");
        // System.out.println("\t\t\t\tPROVA DOPO IL SORT : \n" + temp.toString());
        int k = 0;
        for (Pizza p : temp) {
            if (p == (pizza)) {
                break;//schifo -->switch to WHILE!!
            }
            k++;
        }
        //System.out.println("\tPosizione nella lista temp ->" + k);// k mi dice in che posizione è la mia pizza nella lista che ho ordinato
        //System.out.println("***********\t\t\t LA MIA PIZZA \t" + temp.get(k));
        //System.out.println("************PIZZA PIU' SIMILE E'" + temp.get(k - 1));
        // System.out.println(">>>>>>>>>>>>>>>>>>FINE \n");
        // System.out.println("\t\t\t\t 2 MENU PIZZE\n" + printMenuPizze());
        this.currentComanda.getOrdersList().remove(pizza);
        //  System.out.println("\t\t\t\t 3 MENU PIZZE\n" + printMenuPizze());//RIMUOVO LA PIZZA CHE AVEVO MODIFICATO DALLA CURRENTCOMANDA
        // DEVO CONTROLLARE CHE LA POSIZIONE IN CUI SI E' CLASSIFICATA LA MIA PIZZA MODIFICATA NON SIA LA PRIMA (OVVERO K == 0)
        if (k == 0) {// SE E' LA PRIMA RIAGGIUNGO LA MIA PIZZA (FORSE CASO IMPOSSIBILE)
            this.currentComanda.addProduct(pizza);
        } else if (pizza.equals(temp.get(k - 1))) {
            this.currentComanda.addProduct(temp.get(k - 1));//questo ha gia i costi base degli ingred

        } else {
            int j = 0;
            boolean exit = false;
            while (!exit) {
                //    System.out.println("\t\t\t\t 4 MENU PIZZE\n" + printMenuPizze());
                //           ATTENZIONE I CLONE QUI SOTTO SON DA RISISTEMARE !!!!!
                ArrayList<Ingredient> candidato = (ArrayList<Ingredient>) temp.get(k - 1 - j).getIngredients().clone();
                ArrayList<Ingredient> modificata = (ArrayList<Ingredient>) pizza.getIngredients().clone();
                ArrayList<Ingredient> modificataPlus = (ArrayList<Ingredient>) pizza.getPlusIngredients().clone();
                modificata.addAll(modificataPlus);
                candidato.removeAll(modificata);
                //   System.out.println("\t\t\t\t 5 MENU PIZZE\n" + printMenuPizze());
                if (candidato.isEmpty()) {
                    //  System.out.println("\t\t\t\t 6 MENU PIZZE\n" + printMenuPizze());
                    // System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n");
                    // se candidato è vuoto -> gli ingredienti base vanno bene devo solo aggiungere gli altri plus
                    modificata.removeAll(temp.get(k - 1 - j).getIngredients());
                    // System.out.println("\t\t\t\t 7 MENU PIZZE\n" + printMenuPizze());
                    for (Ingredient m : modificata) { // devo settare i prezzi degli ingredienti che ho aggiunto in piu
                        m.setPrice(ingredientsManager.getIngredientByName(m.getName()).getPrice());
                    }
                    temp.get(k - 1 - j).getPlusIngredients().addAll(modificata);
                    //   System.out.println("\t\t\t\t 8 MENU PIZZE\n" + printMenuPizze());
                    this.currentComanda.addProduct(temp.get(k - j - 1));
                    exit = true;
                }
                j++;
            }
        }

    }

}
