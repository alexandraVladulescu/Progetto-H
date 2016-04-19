package data;

import java.util.ArrayList;

/**
 *
 * @author Markenos
 */
public class ComandeManager {

    private ArrayList<Comanda> comande;

    public ComandeManager() {
        comande = new ArrayList<Comanda>();
    }

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
            if (surname.equals(com.getClient().getSurname())) {
                c = com;
            }
            break; // fa schifo
        }
        if (c == null) {
            throw new ComandaNotFoundException("\t NESSUNA COMANDA TROVATA CORRISPONDENTE A \t" + surname + "\n");
        }
        return c;
    }
public Comanda getComanda(String surname) throws ComandaNotFoundException{
        Comanda comandaTrovata = this.searchComandaByName(surname);
        return comandaTrovata;
    }


}
