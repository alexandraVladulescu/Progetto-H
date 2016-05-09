package gui.comande_view.comande_list_view;

import data.Comanda;
import data.Pizzeria;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Markenos
 */
public class ComandeListPanel extends JPanel implements Observer, Subject{
    //Il nostro modello
    private Pizzeria pizzeria;
    //ArrayList di righe dove ciascuna riga rappresenta una comanda
    ArrayList<ComandeListPanelLine> comandeLines;
    //L'indice della comanda selezionata
    private int selectedComandaIndex;
    //ATTENZIONE : Queste variabili servono per poter utilizzare il design pattern observer da noi fatto
    private ArrayList<gui.comande_view.comande_list_view.Observer> observers;
    private boolean changed;
    

    public ComandeListPanel(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.comandeLines = new ArrayList<ComandeListPanelLine>();
        this.observers = new ArrayList<gui.comande_view.comande_list_view.Observer>();
        //L'indice parte da -1 perché all'inizio non è selezionata nessuna comanda
        this.selectedComandaIndex = -1;
        
        //Aggiungiamo questo pannello come observer di ComandeManager
        this.pizzeria.getCurrentComandaManager().getComandeManager().addObserver(this);
        
        //Utilizzo un boxLayout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.createComandeGrid();
    }
    
    private void createComandeGrid(){
        //Per ogni comanda presente nella pizzeria
        for(int i = 0; i < pizzeria.getCurrentComandaManager().getComandeManager().getComande().size();i++){
            //Creiamo una nuova linea che rappresenta una comanda
            Comanda comanda = pizzeria.getCurrentComandaManager().getComandeManager().getComande().get(i);
            ComandeListPanelLine comandeListPanelLine = new ComandeListPanelLine(
                    Integer.toString(i+1),
                    comanda.getClient().getName(),
                    comanda.getClient().getSurname(),
                    String.format("%02d:%02d", comanda.getDeliveryTime().get(Calendar.HOUR_OF_DAY), comanda.getDeliveryTime().get(Calendar.MINUTE)),
                    comanda.getTerminated(),
                    this);
            this.comandeLines.add(comandeListPanelLine);
            this.add(comandeListPanelLine);
        }
    }

    public ArrayList<ComandeListPanelLine> getComandeLines() {
        return comandeLines;
    }

    public void setSelectedComandaIndex(int selectedComandaIndex) {
        this.selectedComandaIndex = selectedComandaIndex;
        setChanged();
        notifyObservers();
    }

    public int getSelectedComandaIndex() {
        return selectedComandaIndex;
    }
    
    
    

    @Override
    public void update(Observable o, Object arg) {
        //Puliamo l'arrayList e il pannello dagli elementi contenuti, sennò verrebbero sdoppiati...
        comandeLines.clear();
        this.removeAll();
        this.createComandeGrid();
        this.updateUI();
    }
    
    
    
    
    //ATTENZIONE: Il codice che segue serve ai fini dell'implementazione del nostro design pattern Observer
    @Override
    public void registerObserver(gui.comande_view.comande_list_view.Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(gui.comande_view.comande_list_view.Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        if (changed){
            for (int i = 0; i < observers.size(); i++) {
                observers.get(i).update(this.selectedComandaIndex);
            }
            changed = false;
        }
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    
}
