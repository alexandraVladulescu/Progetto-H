package data;

import exceptions.ComandaNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Questa classe ha il compito di gestire le comande presenti nella pizzeria.
 * E' un singleton.
 * @author Markenos
 */
public class ComandeManager extends Observable {

    private ArrayList<Comanda> comande;
    //Variabile statica singleton
    private static ComandeManager comandeManager;

    private ComandeManager() {
        comande = new ArrayList<Comanda>();
    }
    
   //Metodo utilizzato per il pattern singleton
    public static ComandeManager getInstance(){
        if (comandeManager == null){
            comandeManager = new ComandeManager();
        }
        return comandeManager;
    }
    
//Sposto la creazione di una nuova comanda (che corrisponde con la current comanda) in ComandaManager

    public void addComanda(Comanda c) {
        this.comande.add(c);
        setChanged();
        notifyObservers();
                
    }

    public void removeComanda(String surname) throws ComandaNotFoundException {
        Comanda comandaTrovata = this.searchComandaByName(surname);
        this.comande.remove(comandaTrovata);
        setChanged();
        notifyObservers();
    }

    public Comanda searchComandaByName(String surname) throws ComandaNotFoundException {
        Comanda c = null;
        for (Comanda com : comande) {

            if (surname.equalsIgnoreCase(com.getClient().getSurname())) {
                c = com;
                break;
            }
            // fa schifo
        }
        if (c == null) {
            throw new ComandaNotFoundException("\t NESSUNA COMANDA TROVATA CORRISPONDENTE A \t" + surname + "\n");
        }
        return c;
    }

    public ArrayList<Comanda> getComande() {
        return comande;
    }

    public Comanda getComanda(String surname) throws ComandaNotFoundException {
        Comanda comandaTrovata = this.searchComandaByName(surname);
        return comandaTrovata;
    }

    public String printAllComande() {
        String s = "";
        for (Comanda c : comande) {
            s += "\t" + c.toString() + "\n";
        }
        return s;
    }

}
