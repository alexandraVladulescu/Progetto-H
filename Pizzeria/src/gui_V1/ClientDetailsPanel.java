package gui_V1;

import data.Pizzeria;
import java.awt.GridLayout;
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

    public ClientDetailsPanel(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;
        
        //Il layout è una griglia di 6 righe e 2 colonne
        this.setLayout(new GridLayout(6, 2));
        
        //Creo i vari campi con relative label
        JLabel labelClientSurname = new JLabel("Cognome cliente");
        this.add(labelClientSurname);
        JTextField textClientSurname = new JTextField(5);
        this.add(textClientSurname);
        
        JLabel labelClientAddress = new JLabel("Indirizzo cliente");
        this.add(labelClientAddress);
        JTextField textClientAddress = new JTextField();
        this.add(textClientAddress);
        
        JLabel labelHouseNumber = new JLabel("Numero civico");
        this.add(labelHouseNumber);
        JTextField textHouseNumber = new JTextField();
        this.add(textHouseNumber);
        
        JLabel labelCity = new JLabel("Città");
        this.add(labelCity);
        JTextField textCity = new JTextField();
        this.add(textCity);
        
        JLabel labelClientNumber = new JLabel("Recapito");
        this.add(labelClientNumber);
        JTextField textClientNumber = new JTextField();
        this.add(textClientNumber);
        
        JLabel labelDeliveringHour = new JLabel("Ora consegna");
        this.add(labelDeliveringHour);
        JTextField textDeliveringHour = new JTextField();
        this.add(textDeliveringHour);
    }

}
