package gui.order_details_view;

import data.Address;
import data.Client;
import data.CurrentComandaManager;
import data.Pizzeria;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
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
        
        setBackground(new Color(193, 255, 193));
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
        
        JButton confirmButton = new JButton("OK");
        confirmButton.setBackground(new Color(180, 238, 180));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                setClientToComanda();
            }
        });
        this.add(confirmButton);
        
        JButton clearButton = new JButton("clear");
        clearButton.setBackground(new Color(180, 238, 180));
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                clearAllTextFields();
            }
        });
        this.add(clearButton);
    }
    
    private void setClientToComanda(){
        CurrentComandaManager currentComandaManager = pizzeria.getCurrentComandaManager();
        Address ad = new Address(textCity.getSelectedText(), textClientAddress.getText(), 
                                   textClientNumber.getText());
        Client cl = new Client(textClientName.getText(), textClientSurname.getText(),
                                textClientNumber.getText(), ad);
        
        currentComandaManager.setClientToComanda(cl);
        currentComandaManager.confirmComanda();
        /*TODO: pizzeria.getCurrentComanda().setDeliveryTime(); 
        TODO: Printer.getPrinterSingleton().printOrder();*/
        System.out.println(currentComandaManager.showComandaDetails());
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
