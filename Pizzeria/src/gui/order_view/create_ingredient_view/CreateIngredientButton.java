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
    //Il pannello contenente il form da cui preleviamo i dati da inserire per il nuovo ingrediente
    private CreateIngredientPanel createIngredientPanel;

    public CreateIngredientButton(String name, Pizzeria pizzeria, CreateIngredientPanel createIngredientPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.createIngredientPanel = createIngredientPanel;
        //Aggiungiamo il mouseListener per il pulsante..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                //Se abbiamo lasciato i campi nome e prezzo vuoti segnaliamo un errore con un JDialog
                if (createIngredientPanel.getTextName().getText().equals("") || createIngredientPanel.getTextPrice().getText().equals("")) {
                        createErrorDialog("Campi vuoti", "Devi riempire tutti i campi del form.", CreateIngredientFrame.getIstance(), true);
                } else {
                    //Fa quello che deve controllando che non si verifichino delle eccezioni
                    try {
                        pizzeria.getIngredientsManager().createNewIngredient(createIngredientPanel.getTextName().getText(), Double.parseDouble(createIngredientPanel.getTextPrice().getText()));
                        CreateIngredientFrame.disposeFrame();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } catch (AlreadyExistingIngredientException ex) {
                        createErrorDialog("Ingrediente già esistente", ex.getMessage(), CreateIngredientFrame.getIstance(), true);
                    } catch (NumberFormatException ex){
                        createErrorDialog("Prezzo inserito errato", "Devi inserire un valore numerico per il prezzo.", CreateIngredientFrame.getIstance(), true);
                    }
                }

            }

        });
    }

    //Questo metodo serve per creare una JDialog che segnala un errore generico avente titolo title e testo text
    private void createErrorDialog(String title, String text, JFrame owner, boolean modal) {
        //Se l'ingrediente esiste già, apriamo una jDialog per segnalare ciò...
        JDialog dialogError = new JDialog(owner, modal);
        dialogError.setTitle(title);
        dialogError.setLayout(new BorderLayout());
        //Aggiungiamo il testo alla dialog
        JLabel labelError = new JLabel(text);
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
