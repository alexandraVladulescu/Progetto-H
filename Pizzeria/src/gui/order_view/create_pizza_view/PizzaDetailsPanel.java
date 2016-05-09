package gui.order_view.create_pizza_view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questa classe è il form da cui inserire nome e prezzo della pizza che si vuole creare
 * @author Markenos
 */
public class PizzaDetailsPanel extends JPanel {
     //I campi del nostro form
    JLabel labelName;
    JTextField textName;
    JLabel labelPrice;
    JTextField textPrice;

    public PizzaDetailsPanel() {
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
    }

    public JTextField getTextName() {
        return textName;
    }


    public JTextField getTextPrice() {
        return textPrice;
    }
    
    
    
}
