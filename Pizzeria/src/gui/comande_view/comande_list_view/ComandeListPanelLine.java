package gui.comande_view.comande_list_view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ComandeListPanelLine extends JButton {

    //Il numero della comanda
    private JLabel labelNumeroComanda;
    //Il nome del cliente
    private JLabel labelNameClient;
    //Il cognome del cliente
    private JLabel labelSurnameClient;
    //L'ora di consegna
    private JLabel labelDeliveryTime;
    
    //Il pannello contenente l'indice della pizza selezionata
    private ComandeListPanel comandeListPanel;
    

    public ComandeListPanelLine(String comandaNumber, String comandaClientName, String comandaClientSurname, String comandaDeliveryTime, boolean shipped, ComandeListPanel comandeListPanel) {
        this.labelNumeroComanda = new JLabel(comandaNumber);
        this.labelNameClient = new JLabel(comandaClientName);
        this.labelSurnameClient = new JLabel(comandaClientSurname);
        this.labelDeliveryTime = new JLabel(comandaDeliveryTime);
        this.comandeListPanel = comandeListPanel;

        //Layout di una griglia da 1 riga e 5 colonne
        this.setLayout(new GridLayout(1, 5));

        //Settiamo la dimensione per la linea
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8)));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8)));

        //Se la comanda è già stata evasa, colora il pannello con lo sfondo rosso (imposta un immagine come sfondo)
        if (shipped) {
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
                //e impostiamo l'indice di comanda nel pannello genitore all'indice trovato...
                getComandeListPanel().setSelectedComandaIndex(getComandeListPanel().getComandeLines().indexOf(getThis()));
                //DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
                System.out.println(getComandeListPanel().getSelectedComandaIndex());
            }
            
});
    }
    
    //Serve per poter richiamare this all'interno di mouseClicked()
    private ComandeListPanelLine getThis(){
        return this;
    }

    public ComandeListPanel getComandeListPanel() {
        return comandeListPanel;
    }
    
    

    
    
}
