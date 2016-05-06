package data;

import exceptions.ComandaNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Markenos
 */
public class ComandeManager {

    private ArrayList<Comanda> comande;
    private Comanda currentComanda;

    public ComandeManager() {
        comande = new ArrayList<Comanda>();
    }
//Sposto la creazione di una nuova comanda (che corrisponde con la current comanda) in ComandaManager

    public void addComanda(Comanda c) {
        this.comande.add(c);
    }

    public void removeComanda(String surname) throws ComandaNotFoundException {
        Comanda comandaTrovata = this.searchComandaByName(surname);
        this.comande.remove(comandaTrovata);
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
