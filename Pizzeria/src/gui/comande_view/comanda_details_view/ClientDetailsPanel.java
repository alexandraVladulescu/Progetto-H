package gui.comande_view.comanda_details_view;

import data.Comanda;
import data.CurrentComandaManagerModality;
import data.Pizzeria;
import exceptions.ComandaNotFoundException;
import gui.comande_view.comande_list_view.ComandeListPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Questo pannello contiene i dettagli del cliente
 *
 * @author Markenos
 */
public class ClientDetailsPanel extends JPanel {

    private Pizzeria pizzeria;

    private JLabel clientName;
    private JLabel clientSurname;
    private JLabel clientAddress;
    private JLabel houseNumber;
    private JLabel city;
    private JLabel clientNumber;
    private JLabel deliveringHour;
    //Il pulsate che setta la comanda a terminata o non terminata
    private JButton setShippedButton;
    //La comanda attualmente selezionata
    private Comanda selectedComanda;

    public ClientDetailsPanel(Pizzeria pizzeria) {

        this.pizzeria = pizzeria;

        //Di default è a null la oomanda selezionata
        this.selectedComanda = null;

        //Creo i vari campi con relative label
        JLabel labelClientName = new JLabel("Nome cliente");
        this.clientName = new JLabel("");

        JLabel labelClientSurname = new JLabel("Cognome cliente");
        clientSurname = new JLabel("");

        JLabel labelClientAddress = new JLabel("Indirizzo cliente");
        clientAddress = new JLabel("");

        JLabel labelHouseNumber = new JLabel("Numero civico");
        houseNumber = new JLabel("");

        JLabel labelCity = new JLabel("Città");
        city = new JLabel("");

        JLabel labelClientNumber = new JLabel("Recapito");
        clientNumber = new JLabel("");

        JLabel labelDeliveringHour = new JLabel("Ora consegna");
        deliveringHour = new JLabel("");

        //Pulsante che serve per settare a evasa o non evasa la comanda
        setShippedButton = new JButton("");

        setShippedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //Se c'è una comanda selezionata
                if (getSelectedComanda() != null) //Se l'odine è già stato evaso
                {
                    try {
                        if (getSelectedComanda().getTerminated()) {
                            //Imposta a non evaso
                            pizzeria.getCurrentComandaManager().getComandeManager().getComandaById(getSelectedComanda().getId()).setTerminated(true);
                            setShippedButton.setText("Evasa");
                        } else {
                            //Imposta a evaso
                            pizzeria.getCurrentComandaManager().getComandeManager().getComandaById(getSelectedComanda().getId()).setTerminated(false);
                            setShippedButton.setText("Non evasa");
                        }
                    }
                        catch (ComandaNotFoundException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }

                }
            }
            );

        //Pulsante che serve per modificare la comanda corrente
        JButton modifyComandaButton = new JButton("Modifica comanda");

            modifyComandaButton.addMouseListener ( 
                new MouseAdapter() {

            @Override
                public void mouseClicked(MouseEvent e) {
                //Se c'è una comanda selezionata
                if (getSelectedComanda() != null) {
                        try {
                            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                            //Mi metto in modalità di modifica comanda settando selectedComanda come la comanda corrente (da modificare)
                            pizzeria.getCurrentComandaManager().setModality(CurrentComandaManagerModality.MODIFY);
                            pizzeria.getCurrentComandaManager().setCurrentComanda(getSelectedComanda());
                            //Voglio tornare alla schermata precedente
                            JTabbedPane tabbedPane = getParentTabbedPane();
                            tabbedPane.setSelectedIndex(0);
                        } catch (CloneNotSupportedException ex) {
                            System.err.println("Errore durante la clonazione di Comanda in ClientDetailsPanel (in comande attive).");
                        }
                    }

                }

            }
            );
        //Pulsante che serve per eliminare la comanda
        JButton deleteComandaButton = new JButton("Delete comanda");

            deleteComandaButton.addMouseListener ( 
                new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent me) {
                //Se abbiamo una comanda selezionata
                if (getSelectedComanda() != null) {
                        try {
                            pizzeria.getCurrentComandaManager().getComandeManager().removeComandaById(selectedComanda.getId());
                            setSelectedComanda(null);
                        } catch (ComandaNotFoundException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }
            );

        //Utilizzo un GridBagLayout così da poter dare un "peso" in termini di grandezza ad ogni riga della tabella
        GridBagLayout layout = new GridBagLayout();
            GridBagConstraints lim = new GridBagConstraints();
             

            this.setLayout(layout);

            //Prima riga: label nome del cliente
            lim.gridx  = 0;
            lim.gridy  = 0;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelClientName, lim);
             

            this.add(labelClientName);

            lim.gridx  = 1;
            lim.gridy  = 0;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (clientName, lim);
             

            this.add(clientName);

            //seconda riga: label congnome del cliente
            lim.gridx  = 0;
            lim.gridy  = 1;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelClientSurname, lim);
             

            this.add(labelClientSurname);

            lim.gridx  = 1;
            lim.gridy  = 1;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (clientSurname, lim);
             

            this.add(clientSurname);

            //Terza riga: label nome città del cliente
            lim.gridx  = 0;
            lim.gridy  = 2;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelCity, lim);
             

            this.add(labelCity);

            lim.gridx  = 1;
            lim.gridy  = 2;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (city, lim);
             

            this.add(city);

            //Quarta riga: label indirizzo del cliente
            lim.gridx  = 0;
            lim.gridy  = 3;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelClientAddress, lim);
             

            this.add(labelClientAddress);

            lim.gridx  = 1;
            lim.gridy  = 3;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (clientAddress, lim);
             

            this.add(clientAddress);

            //Quinta riga: label numero civico del cliente
            lim.gridx  = 0;
            lim.gridy  = 4;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelHouseNumber, lim);
             

            this.add(labelHouseNumber);

            lim.gridx  = 1;
            lim.gridy  = 4;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (houseNumber, lim);
             

            this.add(houseNumber);

            //Sesta riga: label recapito del cliente
            lim.gridx  = 0;
            lim.gridy  = 5;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelClientNumber, lim);
             

            this.add(labelClientNumber);

            lim.gridx  = 1;
            lim.gridy  = 5;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (clientNumber, lim);
             

            this.add(clientNumber);

            //Settima riga: label ora di consegna
            lim.gridx  = 0;
            lim.gridy  = 6;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (labelDeliveringHour, lim);
             

            this.add(labelDeliveringHour);

            lim.gridx  = 1;
            lim.gridy  = 6;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;
            lim.gridx  = 2;

            layout.setConstraints (deliveringHour, lim);
             

            this.add(deliveringHour);

            //Ottava riga: pulsanti di Evasa/Non evasa, Modifica comanda ed elimina comanda...
            lim.gridx  = 0;
            lim.gridy  = 7;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (setShippedButton, lim);
             

            this.add(setShippedButton);

            lim.gridx  = 1;
            lim.gridy  = 7;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (modifyComandaButton, lim);
             

            this.add(modifyComandaButton);

            lim.gridx  = 2;
            lim.gridy  = 7;
            lim.weightx  = 1;
            lim.weighty  = 1;
            lim.fill  = GridBagConstraints.BOTH;

            layout.setConstraints (deleteComandaButton, lim);
             

            this.add(deleteComandaButton);

            //Impostiamo a vuote le label
             

            this.clearAllFields();
        }

    private void clearAllFields() {
        city.setText("");
        clientAddress.setText("");
        clientName.setText("");
        clientNumber.setText("");
        clientSurname.setText("");
        houseNumber.setText("");
        deliveringHour.setText("");
    }

    public void update(Comanda comanda) {
        this.selectedComanda = comanda;
        //Aggiorniamo le label con i dati del cliente
        this.city.setText(selectedComanda.getClient().getAddress().getLocalityName());
        this.clientAddress.setText(selectedComanda.getClient().getAddress().getAddress());
        this.houseNumber.setText(selectedComanda.getClient().getAddress().getHouseNumber());
        this.clientName.setText(selectedComanda.getClient().getName());
        this.clientSurname.setText(selectedComanda.getClient().getSurname());
        this.clientNumber.setText(selectedComanda.getClient().getAddress().getInformations());
        this.deliveringHour.setText(String.format(
                "%02d:%02d",
                selectedComanda.getDeliveryTime().get(Calendar.HOUR_OF_DAY),
                selectedComanda.getDeliveryTime().get(Calendar.MINUTE)));
        //Se c'è una comanda selezionata...
        if (selectedComanda != null) {
            //Se l'ordine è stato terminato
            if (selectedComanda.getTerminated()) {
                setShippedButton.setText("Evasa");
            } else {
                setShippedButton.setText("Non evasa");
            }
        }
    }

    //Questo metodo serve per ottenere il JTabbedPane con le schede "Crea/Modifica ordine" e "Comande attive"
    private JTabbedPane getParentTabbedPane() {
        Object object = null;
        object = this.getParent();
        //Finché non otteniamo un JTabbedPane
        while (!(object instanceof JTabbedPane)) {
            object = ((JComponent) object).getParent();
        }
        return (JTabbedPane) object;
    }

    //Serve per utilizzarlo nei metodi dei mouseAdapter (in modo che non si creino casini)
    public Comanda getSelectedComanda() {
        return selectedComanda;
    }
    //come sopra...
    public void setSelectedComanda(Comanda selectedComanda) {
        this.selectedComanda = selectedComanda;
    }
    
    

}
