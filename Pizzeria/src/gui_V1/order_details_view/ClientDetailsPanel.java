package gui_V1.order_details_view;

import data.Address;
import data.Client;
import data.Pizzeria;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
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

    public ClientDetailsPanel(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;
        
        //Il layout è una griglia di 6 righe e 2 colonne
        this.setLayout(new GridLayout(6, 2));
        
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
        
        JButton confirmButton = new JButton("OK");
        this.add(confirmButton);
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                setClientToComanda();
            }
        });
        
        JButton clearButton = new JButton("clear");
        this.add(clearButton);
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                clearAllTextFields();
            }
        });
    }
    
    private void setClientToComanda(){
        Address ad = new Address(textCity.getText(), textClientAddress.getText(), 
                                   textClientNumber.getText());
        Client cl = new Client(textClientName.getText(), textClientSurname.getText(),
                                textClientNumber.getText(), ad);
        
        pizzeria.getCurrentComanda().setClient(cl);
        System.out.println(pizzeria.showComandaDetails());
    }
    
    private void clearAllTextFields(){
        textCity.setText("");
        textClientAddress.setText("");
        textClientName.setText("");
        textClientNumber.setText("");
        textClientSurname.setText("");
        textClientSurname.setText("");
        textDeliveringHour.setText("");
        textHouseNumber.setText("");
    }

}
