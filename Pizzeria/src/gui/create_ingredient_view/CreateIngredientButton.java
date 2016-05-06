package gui.create_ingredient_view;

import data.Pizzeria;
import exceptions.AlreadyExistingIngredientException;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Markenos
 */
public class CreateIngredientButton extends JButton {

    private Pizzeria pizzeria;
    //Questa variabile serve per chiudere il frame genitore...
    private JFrame frameGenitore;
    //Il pannello contenente il form da cui preleviamo i dati da inserire per il nuovo ingrediente
    private CreateIngredientPanel createIngredientPanel;

    public CreateIngredientButton(String name, Pizzeria pizzeria, JFrame frameGenitore, CreateIngredientPanel createIngredientPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.frameGenitore = frameGenitore;
        this.createIngredientPanel = createIngredientPanel;
        //Aggiungiamo il mouseListener per il pulsante..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                try {
                    pizzeria.getIngredientsManager().createNewIngredient(createIngredientPanel.getTextName().getText(), Double.parseDouble(createIngredientPanel.getTextPrice().getText()));
                    frameGenitore.dispose();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (AlreadyExistingIngredientException ex) {
                    //Se l'ingrediente esiste già, apriamo una jDialog per segnalare ciò...
                    JDialog dialogError = new JDialog(frameGenitore, true);
                    dialogError.setTitle("Ingrediente già esistente");
                    dialogError.setLayout(new BorderLayout());
                    //Aggiungiamo il testo alla dialog
                    JLabel labelError = new JLabel(ex.getMessage());
                    labelError.setHorizontalAlignment(JLabel.CENTER);
                    dialogError.add(BorderLayout.CENTER, labelError);
                    //Aggiungo il pulsante di ok
                    JButton buttonOk = new JButton("Ok");
                    //In realtà non serve a molto questo pulsante in quanto basta cliccare sulla X in alto a destra per
                    //ottenere lo stesso comportamento...diciamo che è stato fatto per questioni di "bellezza"...
                    buttonOk.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                            dialogError.dispose();
                        }

                    });
                    dialogError.add(BorderLayout.SOUTH, buttonOk);
                    dialogError.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogError.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 6);
                    //Per centrarlo sullo schermo...anche se non lo centra in verita...
                    //Ragionamento fatto: assegno come posizione x e y la metà della dimensione dello schermo e poi
                    //tolgo a quanto trovato la metà della dimensione del frame stesso...tutto questo per centrarlo!
                    dialogError.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - ((int) dialogError.getSize().getWidth() / 2), ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - ((int) dialogError.getSize().getHeight() / 2));

                    dialogError.setVisible(true);

                }
            }

        });
    }

}
