package gui.comande_view.comande_list_view;

import data.Comanda;
import data.Pizzeria;
import gui.comande_view.comanda_details_view.ComandaDetailsPanel;
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
public class ComandeListPanel extends JPanel implements Observer{
    //Il nostro modello
    private Pizzeria pizzeria;
    //ArrayList di righe dove ciascuna riga rappresenta una comanda
    ArrayList<ComandeListPanelLine> comandeLines;
    //Serve per poter settare questo pannello come observer di ciascuna linea
    private ComandaDetailsPanel comandaDetailsPanel;
    

    public ComandeListPanel(Pizzeria pizzeria, ComandaDetailsPanel comandaDetailsPanel) {
        this.pizzeria = pizzeria;
        this.comandeLines = new ArrayList<ComandeListPanelLine>();
        this.comandaDetailsPanel = comandaDetailsPanel;
        
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
            ComandeListPanelLine comandeListPanelLine = new ComandeListPanelLine(comanda);
            this.comandeLines.add(comandeListPanelLine);
            this.add(comandeListPanelLine);
        }
    }

    public ArrayList<ComandeListPanelLine> getComandeLines() {
        return comandeLines;
    }

    
    
    

    @Override
    public void update(Observable o, Object arg) {
        //Puliamo l'arrayList e il pannello dagli elementi contenuti, sennò verrebbero sdoppiati...
        comandeLines.clear();
        this.removeAll();
        this.createComandeGrid();
        this.updateUI();
        for(ComandeListPanelLine comandeListPanelLine : this.getComandeLines()){
            comandeListPanelLine.registerObserver(comandaDetailsPanel);
        }
    }
    
    
    
}
