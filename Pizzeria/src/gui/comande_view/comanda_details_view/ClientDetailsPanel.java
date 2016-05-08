package gui.comande_view.comanda_details_view;

import gui.order_view.order_details_view.*;
import data.Address;
import data.Client;
import data.CurrentComandaManager;
import data.Pizzeria;
import gui.MainFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questo pannello contiene i dettagli del cliente
 *
 * @author Markenos
 */
public class ClientDetailsPanel extends JPanel {
    
    private Pizzeria pizzeria;
    
    private JTextField textClientName;
    private JTextField textClientSurname;
    private JTextField textClientAddress;
    private JTextField textHouseNumber;
    private JTextField textCity;
    private JTextField textClientNumber;
    private JTextField textDeliveringHour;
    
    //L'indice della comanda selezionata all'interno dell'elenco delle comande
    private int index;
    
    public ClientDetailsPanel(Pizzeria pizzeria) {
        
        this.pizzeria = pizzeria;
        //Di default l'indice è uguale a -1 (nessuna comanda selezionata
        this.index = -1;

        //Il layout è una griglia di 8 righe e 2 colonne
        this.setLayout(new GridLayout(8, 2));

        //Creo i vari campi con relative label
        JLabel labelClientName = new JLabel("Nome cliente");
        this.add(labelClientName);
        textClientName = new JTextField(5);
        this.add(textClientName);
        
        JLabel labelClientSurname = new JLabel("Cognome cliente");
        this.add(labelClientSurname);
        textClientSurname = new JTextField(5);
        this.add(textClientSurname);
        
        JLabel labelClientAddress = new JLabel("Indirizzo cliente");
        this.add(labelClientAddress);
        textClientAddress = new JTextField();
        this.add(textClientAddress);
        
        JLabel labelHouseNumber = new JLabel("Numero civico");
        this.add(labelHouseNumber);
        textHouseNumber = new JTextField();
        this.add(textHouseNumber);
        
        JLabel labelCity = new JLabel("Città");
        this.add(labelCity);
        textCity = new JTextField();
        this.add(textCity);
        
        JLabel labelClientNumber = new JLabel("Recapito");
        this.add(labelClientNumber);
        textClientNumber = new JTextField();
        this.add(textClientNumber);
        
        JLabel labelDeliveringHour = new JLabel("Ora consegna");
        this.add(labelDeliveringHour);
        textDeliveringHour = new JTextField();
        this.add(textDeliveringHour);
        
        JButton setShippedButton = new JButton("");
        setShippedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //TO DO
            }
        });
        this.add(setShippedButton);
        
        JButton deleteComandaButton = new JButton("");
        deleteComandaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //TO DO
            }
        });
        this.add(deleteComandaButton);

        //Impostiamo a vuote le textBox
        this.clearAllTextFields();
    }
    
    
    
    private void clearAllTextFields() {
        textCity.setText("");
        textClientAddress.setText("");
        textClientName.setText("");
        textClientNumber.setText("");
        textClientSurname.setText("");
        textHouseNumber.setText("");
        textDeliveringHour.setText("");
    }

    public void update(int selectedComandaIndex){
        
    }
    
}
