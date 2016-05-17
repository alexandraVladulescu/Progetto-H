package gui.comande_view.comande_list_view;

import data.Comanda;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Questa classe rappresenta una riga all'interno della lista delle comande
 * nella vista "Storico comande". Una linea è composta da n° comanda, nomme e
 * cognome del cliente, ora di consegna e un pannellino rosso o verde a seconda
 * che la comanda sia già stata evasa o no
 *
 * @author Markenos
 */
public class ComandeListPanelLine extends JButton implements Subject{

    //Il numero della comanda
    private JLabel labelNumeroComanda;
    //Il nome del cliente
    private JLabel labelNameClient;
    //Il cognome del cliente
    private JLabel labelSurnameClient;
    //L'ora di consegna
    private JLabel labelDeliveryTime;
    //La comanda legata a questo pulsante
    private Comanda comanda;
    
    //ATTENZIONE : Queste variabili servono per poter utilizzare il design pattern observer da noi fatto
    private ArrayList<gui.comande_view.comande_list_view.Observer> observers;
    private boolean changed;
    
    public ComandeListPanelLine(Comanda comanda) {
        this.comanda = comanda;
        this.observers = new ArrayList<gui.comande_view.comande_list_view.Observer>();
        //Inizializzo le label con i dati della comanda
        this.labelNumeroComanda = new JLabel(Integer.toString(comanda.getId()));
        this.labelNameClient = new JLabel(comanda.getClient().getName());
        this.labelSurnameClient = new JLabel(comanda.getClient().getSurname());
        this.labelDeliveryTime = new JLabel(String.format("%02d:%02d", comanda.getDeliveryTime().get(Calendar.HOUR_OF_DAY), comanda.getDeliveryTime().get(Calendar.MINUTE)));

        //Layout di una griglia da 1 riga e 5 colonne
        this.setLayout(new GridLayout(1, 5));

        //Settiamo la dimensione per la linea
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8)));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8)));

        //Se la comanda è già stata evasa, colora il pannello con lo sfondo rosso (imposta un immagine come sfondo)
        if (comanda.getTerminated()) {
//            ImageIcon imageIcon = new ImageIcon("./graphics/backgrounds/not_shipped_background.png");
//            Image image = imageIcon.getImage();
//            Image newImage = image.getScaledInstance((int)this.getMaximumSize().getWidth(), (int)this.getMaximumSize().getHeight(), Image.SCALE_SMOOTH);
//            imageIcon.setImage(newImage);
//            this.setIcon(imageIcon);
            ImageIcon imageIcon = new ImageIcon("./graphics/backgrounds/shipped_background.png");
            this.setIcon(imageIcon);

        } else { //altrimenti con uno sfondo verde
            ImageIcon imageIcon = new ImageIcon("./graphics/backgrounds/not_shipped_background.png");
            this.setIcon(imageIcon);
        }

        //Aggiungiamo gli elementi al panel
        this.add(labelNumeroComanda);
        this.add(labelNameClient);
        this.add(labelSurnameClient);
        this.add(labelDeliveryTime);
        
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Otteniamo l'indice di questo elemento all'interno del pannello genitore
                setChanged();
                notifyObservers();
            }
            
});
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
                observers.get(i).update(comanda);
            }
            changed = false;
        }
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    
    
}
