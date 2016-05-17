package data;

import exceptions.ComandaNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Questa classe ha il compito di gestire le comande presenti nella pizzeria. E'
 * un singleton.
 *
 * @author Markenos
 */
public class ComandeManager extends Observable {

    private ArrayList<Comanda> comande;
    //Variabile statica singleton
    private int count = -1;
    private static ComandeManager comandeManager;

    private ComandeManager() {
        comande = new ArrayList<Comanda>();
    }

    //Metodo utilizzato per il pattern singleton
    public static ComandeManager getInstance() {
        if (comandeManager == null) {
            comandeManager = new ComandeManager();
        }
        return comandeManager;
    }

    public int generateId() {
        count++;
        return count;
    }
//Sposto la creazione di una nuova comanda (che corrisponde con la current comanda) in ComandaManager

    public void addComanda(Comanda c) {
        this.comande.add(c);
        setChanged();
        notifyObservers();

    }

//    public void removeComandaById(String surname) throws ComandaNotFoundException {
//        Comanda comandaTrovata = this.searchComandaBySurname(surname);
//        this.comande.remove(comandaTrovata);
//        setChanged();
//        notifyObservers();
//    }
    public void removeComandaById(int id) throws ComandaNotFoundException {
        //Se l'indice passato è errato
//        if (index < 0 || index >= comande.size()) {
//            throw new ComandaNotFoundException("La comanda da te cercata di indice " + index + " non esiste.");
//        }
        Iterator i = comande.iterator();
        boolean b = true;
        Comanda c;
        Comanda trovata = new Comanda();
        while (i.hasNext()) {
            c = (Comanda) i.next();
            if (c.getId() == id) {
                b = false;
                trovata = c;
            }
        }
        if (b) {

            throw new ComandaNotFoundException("Comanda non trovata!");

        }
        comande.remove(trovata);
        setChanged();
        notifyObservers();
        //indice dell'arrayList delle comande prsenti nello storico
    }

    public Comanda getComandaById(int id) throws ComandaNotFoundException {
        Iterator i = comande.iterator();
        Comanda c;
        while (i.hasNext()) {
            c = (Comanda) i.next();
            //System.out.println("id" + c.getId());
            if (c.getId() == id) {
                setChanged();
                notifyObservers();
                return c;
            }
        }
        throw new ComandaNotFoundException("Comanda non trovata per id ==\t" + id);

    }

    public ArrayList<Comanda> searchComandaBySurname(String surname) throws ComandaNotFoundException {
        ArrayList<Comanda> foundComande = new ArrayList<Comanda>();
        for (Comanda com : comande) {

            if (surname.equalsIgnoreCase(com.getClient().getSurname())) {
                foundComande.add(com);
            }
        }
        if (foundComande.isEmpty()) {
            throw new ComandaNotFoundException("\t NESSUNA COMANDA TROVATA CORRISPONDENTE A \t" + surname + "\n");
        }
        return foundComande;
    }

    public ArrayList<Comanda> getComande() {
        return comande;
    }

//    public Comanda getComanda(String surname) throws ComandaNotFoundException {
//        Comanda comandaTrovata = this.searchComandaBySurname(surname);
//        return comandaTrovata;
//    }
    public String printAllComande() {
        String s = "";
        for (Comanda c : comande) {
            s += "\t" + c.toString() + "\n";
        }
        return s;
    }
    
    public void setTerminatedById(int id, boolean terminated) throws ComandaNotFoundException{
        this.getComandaById(id).setTerminated(terminated);
        setChanged();
        notifyObservers();
    }

}
