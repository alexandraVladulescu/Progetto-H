/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ClientDetailsPanel extends JPanel{
    
    public ClientDetailsPanel() {

        this.setLayout(new GridLayout(6, 2));
        JLabel lblClientSurname = new JLabel("Cognome cliente");
        this.add(lblClientSurname);
        JTextField txtClientSurname = new JTextField(5);
        this.add(txtClientSurname);
        JLabel lblClientAddress = new JLabel("Indirizzo cliente");
        this.add(lblClientAddress);
        JTextField txtClientAddress = new JTextField();
        this.add(txtClientAddress);
        JLabel lblHouseNumber = new JLabel("Numero civico");
        this.add(lblHouseNumber);
        JTextField txtHouseNumber = new JTextField();
        this.add(txtHouseNumber);
        JLabel lblCity = new JLabel("Città");
        this.add(lblCity);
        JTextField txtCity = new JTextField();
        this.add(txtCity);
        JLabel lblClientNumber = new JLabel("Recapito");
        this.add(lblClientNumber);
        JTextField txtClientNumber = new JTextField();
        this.add(txtClientNumber);
        JLabel lblDeliveringHour = new JLabel("Ora consegna");
        this.add(lblDeliveringHour);
        JTextField txtDeliveringHour = new JTextField();
        this.add(txtDeliveringHour);

    }
}
    

