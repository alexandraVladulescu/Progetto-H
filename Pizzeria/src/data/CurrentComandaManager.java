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
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author Francesco
 */
public class CurrentComandaManager extends Observable {

    private Comanda currentComanda;
    private ComandeManager comandeManager;
    private MenuPizze menuPizze;// diventato Singleton
    private IngredientsManager ingredientsManager; //diventato Singleton
    //Indica se siamo in modalità di creazione di una nuova comanda o se stiamo modificando una comanda già esistente (di default è CREATE)
    private CurrentComandaManagerModality modality;

    public CurrentComandaManager() {
        comandeManager = new ComandeManager();
        menuPizze = MenuPizze.getInstance();// setto il menuPizze con l'unica istanza che esiste
        ingredientsManager = IngredientsManager.getInstance();// setto il menu degli ingredienti con l'unica istanza
        this.modality = CurrentComandaManagerModality.CREATE;
    }

    public Comanda createComanda() {
        currentComanda = new Comanda();
        currentComanda.setId(comandeManager.generateId());
        setChanged();
        notifyObservers();
        return currentComanda;// Crea la comanda e la restituisce al chiamante. NB: non viene aggiunta all'arrayList
    }

    public void setCurrentComanda(Comanda currentComanda) throws CloneNotSupportedException {// Setta la comanda con parametro oggetto comanda
        if (modality == modality.CREATE) {
            this.currentComanda = currentComanda;
            setChanged();
            notifyObservers();
        } else if (modality == modality.MODIFY) {
            this.currentComanda = (Comanda) currentComanda.clone();
            setChanged();
            notifyObservers();
        }
    }

    public ArrayList<Comanda> getComandeBySurname(String surname) throws ComandaNotFoundException {
        return this.comandeManager.searchComandaBySurname(surname);

    }

//    public void setCurrentComanda(String clientSurname) throws ComandaNotFoundException {// Setta la comanda da String,
//        Comanda comandaTrovata = this.comandeManager.searchComandaBySurname(clientSurname);//Controlla se esiste e se la fa restituire
//        this.setCurrentComanda(comandaTrovata);//Usa il metodo setCurrentComanda con parametro oggetto
//    }
    public Comanda getCurrentComanda() {
        return currentComanda;
    }

    public void confirmComanda() throws ComandaNotFoundException {
        if (this.modality == CurrentComandaManagerModality.MODIFY) {
            //Se sono qui son i MODIFY vuol dire che devo fare prima il remove
//             System.out.println("id"+currentComanda.getId());
            this.comandeManager.removeComandaById(currentComanda.getId());

            this.setModality(CurrentComandaManagerModality.CREATE);
        }
        //In ogni caso devo aggiungere la currentComanda
        this.comandeManager.addComanda(currentComanda);
//        DOPO QUALSIASI CONFIRM CREO GIA' UNA NUOVA COMANDA ALTRIMENTI MI RIMANE SETTATA LA CURRENT COMANDA
//        CON QUELLA PRECEDENTE
        this.createComanda();
        setChanged();
        notifyObservers();

    }

    public void setClientToComanda(Client c) {
        this.currentComanda.setClient(c);
    }

    public CurrentComandaManagerModality getModality() {
        return modality;
    }

    public void setModality(CurrentComandaManagerModality modality) {
        this.modality = modality;
    }

    public void addPizza(String nomePizza) throws PizzaNotFoundInMenuException, CloneNotSupportedException {
        DescriptionPizza d = menuPizze.getPizzaByName(nomePizza);
        if (d != null) {

            this.currentComanda.addPizza(new Pizza(d));
            setChanged();
            notifyObservers();
        }

    }

    public ComandeManager getComandeManager() {
        return comandeManager;
    }

    public void addPizza(Pizza p) {
        this.currentComanda.addPizza(p);
        setChanged();
        notifyObservers();
    }

    public String showComandaDetails() {
        return this.currentComanda.toString();
    }

    public void removePizza(int index) throws ProductNotFoundException {
        this.currentComanda.removePizza(index);
        setChanged();
        notifyObservers();
    }

    private boolean algoritmo(Pizza pizza, ArrayList<Ingredient> allIngredients) throws IngredientNotFoundException, CloneNotSupportedException {

        ArrayList<DescriptionPizza> copyMenu = new ArrayList<>();
        copyMenu.addAll(menuPizze.getPizze());
        copyMenu.sort(new DescriptionPizza.ComparatorDescriptionPizzaSize());
        //  System.out.println("_________________________________ingrediente"+ingredient.toString());
        //  System.out.println("___________SORT_______________________________\n"+copyMenu.toString());
        boolean exit = false;
        int i = copyMenu.size() - 1;
        ArrayList<Ingredient> ingredientRunTime = new ArrayList<>();
        ArrayList<DescriptionPizza> candidate = new ArrayList<>();// mi serve dopo
        while ((!exit) && (i >= 0)) {
            ingredientRunTime.clear();
            ingredientRunTime.addAll(copyMenu.get(i).getIngredients());//metto gli ingredienti dell'i-esima Description
            int size = ingredientRunTime.size();// tengo la size perchè mi serve dopo per confrontarla con la size
            //degli ingredienti della mia pizza
            ingredientRunTime.removeAll(allIngredients);// "sottrazione"
            if (ingredientRunTime.isEmpty()) {// se è vuoto non è detto che l'abbia trovata ma il gruppo è quello!
                //quindi in ogni caso devo uscire dal while esterno
                candidate.add(copyMenu.get(i));// in ogni caso ho una Description candidata che potrebbe non esser
                //l'unica!
                exit = true;
                if (size == allIngredients.size()) {// se è vuoto ed è della stessa size ho trovato una Description 
                    // che combacia essattamente.Posso uscire
                    exit = true;// si può togliere
                } else {//MACELLO! NB: entro solo se è verificato il primo if ma non il secondo!
                    // Se son qui vuol dire che la differenza tra ingredienti è zero ma c'è qualcosa da aggiungere
                    //In questo caso devo tener conto che la Description su cui mi trovo potrebbe non esser la scelta migliore
                    //può essercene un'altra sopra di lei che da differenza zero!
                    boolean stop = false;
                    i--; // Decremento perchè devo partire dalla posizione successiva verso l'alto
                    while ((!stop) && (i >= 0)) {
                        ingredientRunTime.clear();
                        ingredientRunTime.addAll(copyMenu.get(i).getIngredients());
                        int size1 = ingredientRunTime.size();
                        ingredientRunTime.removeAll(allIngredients);// "sottrazione"
                        if ((size != size1)) {
                            stop = true; //se entro qui vuol dire che sto entrando nelle description che non mi interessano
                            //ovvero quelle acnora più sopra con ingredienti in meno rispetto a quelli con cui avevo trovato la prima
                            //corrispondenza
                        } else if (ingredientRunTime.isEmpty()) {
                            // ok il size è lo stesso ma per far parte dei candidati devo controllare
                            //che la differenza sia zero! (questo perchè son ordinate solo in base alla size degli ingredienti)
                            candidate.add(copyMenu.get(i));
                        }
                        i--;//decremento while interno stesso index del while esterno
                    }

                }//chiude l'else
            }
            i--; // decremento while esterno
        }//Note : se entro nel while interno, una volta che finisce di iterare finisce anche il while esterno. 
        //Quando esco da tutto questo blocco dovrei trovare in candidate le Description candidate
        // System.out.println("_______________________________candidate: "+candidate.toString());
        if (candidate.isEmpty()) {//se è vuoto non abbiam trovato nulla , allora posso solo aggiungere 
            //l'ingrediente a PlusIngredient della pizza
            setChanged();
            notifyObservers();
            return false;

        } else {
            //ho trovato qualche candidato devo scegliere il migliore. Quello che costa di meno a parità di 
            //ingredienti
            //USO IL METODO CALCULATESBEST che ritorna la la coppia DescriptionPizza e un arrayList contenente
            // gli ingredienti da aggiungere con già prezzo aggiuntivo
            //NB : UTILIZZO FORSE IMPROPRIO DELL HASPMAP MA FUNZIONALE
            //MI FACCIO RIDARE IL KEYSET TANTO CE N'E' SOLO UNA DENTRO
            HashMap<DescriptionPizza, ArrayList<Ingredient>> bestCoppia = calculatesBest(candidate, allIngredients);
            System.out.println("______________________________________"+allIngredients.toString());
            DescriptionPizza key = bestCoppia.keySet().iterator().next();
            Pizza newPizza = new Pizza(key); // creo una nuova pizza tramite la key che è una descriptionPizza
            newPizza.addPlusIngredients(bestCoppia.get(key));//aggiungo gli ingredienti che mi mancano
            // System.out.println("____________________________PIZZA FINALE:"+newPizza.toString());
            this.currentComanda.addPizza(newPizza);//aggiungo la pizza
            setChanged();
            notifyObservers();
            return true;
        }

    }

    private HashMap<DescriptionPizza, ArrayList<Ingredient>> calculatesBest(ArrayList<DescriptionPizza> candidate, ArrayList<Ingredient> allIngredients) throws IngredientNotFoundException {
        HashMap<DescriptionPizza, ArrayList<Ingredient>> bestCoppia = new HashMap<>();
        ArrayList<Ingredient> runTimeIngredient = new ArrayList<>();
        ArrayList<Ingredient> rimanentiPlus = new ArrayList<>();// mi tiene quelli dopo la differenza
        ArrayList<Ingredient> rimanentiPlusFinal = new ArrayList<>();// qui li metto in caso becco il best
        double bestPrice = 10000;
        double priceTemp = 0;
        DescriptionPizza best = null;
        for (DescriptionPizza candidata : candidate) {
            runTimeIngredient.clear();
            priceTemp = 0;
            runTimeIngredient.addAll(allIngredients);
            runTimeIngredient.removeAll(candidata.getIngredients());
            priceTemp = candidata.getPrice();
            //devo calcolare il prezzo aggiungivo di tutti gli ingredienti che aggiungerei
             rimanentiPlus.clear();
            for (Ingredient ingredient : runTimeIngredient) {
                //mi faccio calcolare il prezzo aggiuntivo interrogando ingredients manager
                //perchè all'interno di allIngredients potrebbe esser settato a zero in caso
                //fosse già presente come ingrediente base!
               
                rimanentiPlus.add(this.ingredientsManager.getIngredientByName(ingredient.getName()));
                priceTemp = priceTemp + this.ingredientsManager.getIngredientByName(ingredient.getName()).getPrice();
            }

            if (priceTemp < bestPrice) {// se il price è minore setto la candidata , il bestprice e gli ingredienti
                //aggiuntivi
                best = candidata;
                rimanentiPlusFinal.clear();
                rimanentiPlusFinal.addAll(rimanentiPlus);
                bestPrice = priceTemp;
            }
        }
        bestCoppia.put(best, rimanentiPlusFinal);
        return bestCoppia;
    }

    public void addIngredientToPizza(String ingredientName, int index) throws ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) this.currentComanda.searchPizzaByIndex(index);
        this.currentComanda.removePizza(index);//LA RIMUOVO LO STESSO E POI LA RIAGGIUNGO PER COERENZA
        //COSI' AD OGNI MODIFICA DOVREI TROVARLA PER ULTIMA, ALTRIMENTI IN ALCUNI CASI POTREI TROVARLA NELLO
        //STESSO POSTO E ALTRE VOLTE NO
        Ingredient ingredient = (Ingredient) ingredientsManager.getIngredientByName(ingredientName).clone();//se viene trovato viene resituito l'ingrediente che vogliamo aggiungere
        //pizza.addPlusIngredient(ingredient);//Add ingrediente all'istanza della pizza in currentComanda
        // System.out.println("\t\t\t\t 1 MENU PIZZE\n" + printMenuPizze());
        ArrayList<Ingredient> allIngredients = pizza.getIngredients();
        allIngredients.addAll(pizza.getPlusIngredients());
        allIngredients.add(ingredient);
      //  System.out.println("_______________________________________"+allIngredients.toString());
        //
        ////
        ///
        if (!this.algoritmo(pizza, allIngredients)) {// IN TEORIA E' IMPOSSIBILE. VUOL DIRE CHE ALLA PIZZA HO
            //TOLTO SIA LA MOZZARELLA CHE IL POMODORO
            pizza.addPlusIngredient(ingredient);// OK
            this.currentComanda.addPizza(pizza);
        }
    }

    public void removeIngredientToPizza(String ingredientName, int index) throws ProductNotFoundException, IngredientNotFoundException, CloneNotSupportedException {//si riferisce alla comanda corrente
        Pizza pizza = (Pizza) this.currentComanda.searchPizzaByIndex(index);
        this.currentComanda.removePizza(index);
        Ingredient ingredient = (Ingredient) ingredientsManager.getIngredientByName(ingredientName).clone();//se viene trovato viene resituito l'ingrediente che vogliamo aggiungere
        if (!pizza.removePlusIngredient(ingredient)) { // PROVO A VEDERE SE RIMUOVER DA PLUS
            if (!pizza.removeIngredient(ingredient)) {// SE NON E' IN PLUS LO RIMUOVO DAGLI ING BASE
                // SE L'INGREDIENTE NON E' IN NESSUNO DEI DUE BISOGNA LANCIARE EXCEPTION !
                //PERCHE' VUOL DIRE CHE STO TOGLIENDO UN INGREDIENTE CHE NON C'E' IN QUELLA PIZZA
            }
        }
        ArrayList<Ingredient> allIngredients = pizza.getIngredients();
        allIngredients.addAll(pizza.getPlusIngredients());
        //allIngredients.add(ingredient);
        //
        ////
        ///
        if (!this.algoritmo(pizza, allIngredients)) {// IN TEORIA E' IMPOSSIBILE. VUOL DIRE CHE ALLA PIZZA HO
            //TOLTO SIA LA MOZZARELLA CHE IL POMODORO
            System.out.println("stai togliendo ingredienti per i quali non ottengo nessuna pizza\n");
            // DEVE LANCIARE EXCEPTION
        }
    }

    //Metodo per ritornare l'ora di consegna della comanda corrente

    public Calendar getDeliveryTime() {
        return this.getCurrentComanda().getDeliveryTime();
    }

    //Metodo per settare l'ora di consegna della comanda corrente
    public void setDeliveryTime(Calendar data) {
        this.getCurrentComanda().setDeliveryTime(data);
        setChanged();
        notifyObservers();
    }

    public void setTerminatedById(int id, boolean terminated) throws ComandaNotFoundException {
        this.comandeManager.getComandaById(id).setTerminated(terminated);
        setChanged();
        notifyObservers();
    }

    public Comanda getComandaById(int id) throws ComandaNotFoundException {
        return this.comandeManager.getComandaById(id);
    }

    public void removeComandaByid(int id) throws ComandaNotFoundException {
        this.comandeManager.removeComandaById(id);
    }

    public ArrayList<Comanda> searchComandaBySurname(String surname) throws ComandaNotFoundException {
        return this.comandeManager.searchComandaBySurname(surname);
    }

    public String printAllComande() {
        return this.comandeManager.printAllComande();
    }

}
