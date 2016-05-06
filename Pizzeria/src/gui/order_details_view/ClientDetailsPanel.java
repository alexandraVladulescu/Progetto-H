package gui.order_details_view;

import data.Address;
import data.Client;
import data.CurrentComandaManager;
import data.Pizzeria;
import java.awt.Color;
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
    private JComboBox comboDeliveringHour;

    public ClientDetailsPanel(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;
        
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
        comboDeliveringHour = new JComboBox();
        //Per ogni 5 minuti che esistono in 24 ore...
        for(int i = 0; i < ((24*60)/5);i++){
            comboDeliveringHour.addItem(createDeliveringHourString(i*5));
        }
        //Aggiungo il listener per la combobox in modo che quando seleziono un orario nella combobox me lo setti come
        //orario di consegna della comanda corrente...
        comboDeliveringHour.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Separo le ore e i minuti dell'ora di consegna in due stringhe separate
                String[] time = ((String)(comboDeliveringHour.getSelectedItem())).split(":");
                //I primi tre campi (Anno, mese e giorno) vengono presi dalla data attuale di sistema
                pizzeria.getCurrentComandaManager().setDeliveryTime(new GregorianCalendar(
                        Calendar.getInstance().get(Calendar.YEAR), 
                        Calendar.getInstance().get(Calendar.MONTH), 
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1])));
                System.out.println(pizzeria.getCurrentComandaManager().getDeliveryTime());
            }
        });
        this.add(comboDeliveringHour);
        
        JButton confirmButton = new JButton("OK");
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                setClientToComanda();
            }
        });
        this.add(confirmButton);
        
        JButton clearButton = new JButton("Clear");
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
        textHouseNumber.setText("");
    }
    
    //Questo metodo restituisce una stringa per indicare l'ora di consegna della pizza dati in input i minuti
    //Viene usato durante la creazione della comboBox...
    private String createDeliveringHourString(int minuts){
        int hours = (int) Math.floor(minuts/60);
        int remnantMinuts = minuts % 60;    //I minuti rimanenti sono il resto della divisione dei minuti totali per 60..
        return String.format("%02d:%02d", hours,remnantMinuts);
        
    }

}
