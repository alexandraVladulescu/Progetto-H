package gui.create_ingredient_view;

import data.Pizzeria;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questo pannello contiene il form per inserire il nuovo ingrediente nella
 * pizzeria
 *
 * @author Markenos
 */
public class CreateIngredientPanel extends JPanel {

    //I campi del nostro form
    JLabel labelName;
    JTextField textName;
    JLabel labelPrice;
    JTextField textPrice;

    public CreateIngredientPanel() {

        //Usiamo una griglio 2x2 come layout
        this.setLayout(new GridLayout(2, 2));

        //Creiamo i vari c...ampi...
        labelName = new JLabel("Nome");
        textName = new JTextField();
        labelPrice = new JLabel("Prezzo");
        textPrice = new JTextField();

        //Aggiungiamo i vari campi
        this.add(labelName);
        this.add(textName);
        this.add(labelPrice);
        this.add(textPrice);
        
        //Impostiamo a stringa vuota il testo dei campi del form
        textName.setText("");
        textPrice.setText("");

    }


    public JTextField getTextName() {
        return textName;
    }


    public JTextField getTextPrice() {
        return textPrice;
    }
    
    
    
}
