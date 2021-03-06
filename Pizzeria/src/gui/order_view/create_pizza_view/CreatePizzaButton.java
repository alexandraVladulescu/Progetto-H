package gui.order_view.create_pizza_view;

import data.Ingredient;
import data.Pizzeria;
import exceptions.AlreadyExistingPizzaException;
import exceptions.IngredientNotFoundException;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Questo pulsante da l'ordine di creare la nuova pizza
 *
 * @author Markenos
 */
public class CreatePizzaButton extends JButton {

    private Pizzeria pizzeria;
    //Il pannello contenente il form da cui preleviamo il nome e il prezzo della pizza che vogliamo creare
    private PizzaDetailsPanel pizzaDetailsPanel;
    //Il pannello che contiene gli ingredienti presenti nella pizzeria che possiamo aggiungere alla pizza che vogliamo creare...
    private IngredientsListPanel pizzasListPanel;

    public CreatePizzaButton(String name, Pizzeria pizzeria, PizzaDetailsPanel pizzaDetailsPanel, IngredientsListPanel pizzasListPanel) {
        this.setText(name);
        this.pizzeria = pizzeria;
        this.pizzaDetailsPanel = pizzaDetailsPanel;
        this.pizzasListPanel = pizzasListPanel;
        //Aggiungiamo il mouseListener per il pulsante..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (pizzaDetailsPanel.getTextName().getText().equals("") || pizzaDetailsPanel.getTextPrice().equals("")) {
                    createErrorDialog("Campi vuoti", "Devi riempire tutti i campi del form.", CreatePizzaFrame.getIstance(), true);
                } else {
                    try {
                        //La lista dei nomi degli ingredienti che vogliamo la pizza abbia
                        ArrayList<String> ingredientsToAdd = new ArrayList<String>();
                        //Per ogni checkBox contenuta nel pannello con gli ingredienti da eliminare...
                        for (JCheckBox checkBox : pizzasListPanel.getCheckIngredients()) {
                            if (checkBox.isSelected()) {
                                ingredientsToAdd.add(checkBox.getText());
                            }
                        }
                        //Creiamo un arrayList di ingredienti che poi aggiungeremo alla nuova pizza
                        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
                        //Per ogni ingrediente che vogliamo aggiungere
                        for (String pizzaName : ingredientsToAdd) {
                            ingredients.add(pizzeria.getIngredientsManager().getIngredientByName(pizzaName));
                        }
                        //Creo la nuova pizza
                        pizzeria.getMenuPizze().createNewPizza(pizzaDetailsPanel.getTextName().getText(), Double.parseDouble(pizzaDetailsPanel.getTextPrice().getText()), ingredients);
                        CreatePizzaFrame.disposeFrame();
                    } catch (IngredientNotFoundException ex) {
                        System.err.println(ex.getMessage());
                    } catch (AlreadyExistingPizzaException ex) {
                        createErrorDialog("Pizza già esistente", ex.getMessage(), CreatePizzaFrame.getIstance(), true);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    } catch (NumberFormatException ex) {
                        createErrorDialog("Prezzo inserito errato", "Devi inserire un valore numerico per il prezzo.", CreatePizzaFrame.getIstance(), true);

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
