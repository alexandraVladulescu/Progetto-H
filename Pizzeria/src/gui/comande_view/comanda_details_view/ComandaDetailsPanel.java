package gui.comande_view.comanda_details_view;

import data.Pizzeria;
import gui.comande_view.comande_list_view.ComandeListPanel;
import gui.comande_view.comande_list_view.Observer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * Questo pannello contiene altri tre pannelli:<br/>
 * 1) Il pannello delle pizze ordinate<br/>
 * 2) Il pannello delle bibite ordinate<br/>
 * 3) Il pannello dei dettagli del cliente della comanda<br/>
 * @author Markenos
 */
public class ComandaDetailsPanel extends JPanel implements Observer{
    private Pizzeria pizzeria;
    
    //I tre pannelli sopra descritti
    private PizzasOrderedPanel pizzasOrderedPanel;
    private PizzasTotalPricePanel pizzasTotalPricePanel;
    private DrinksOrderedPanel drinksOrderedPanel;
    private DrinksTotalPricePanel drinksTotalPricePanel;
    private ComandaTotalPricePanel comandaTotalPricePanel;
    private ClientDetailsPanel clientDetailsPanel;

    public ComandaDetailsPanel(Pizzeria pizzeria, ComandeListPanel comandeListPanel) {
        this.pizzeria = pizzeria;
        
        //A pizzasOrderedPanel e drinksOrderedPanel dobbiamo passare anche la dimensione di questo pannello.
        //Vedi nei relativi file il perché...
        pizzasOrderedPanel = new PizzasOrderedPanel(this.pizzeria);
        pizzasTotalPricePanel = new PizzasTotalPricePanel(this.pizzeria);
        drinksOrderedPanel = new DrinksOrderedPanel(this.pizzeria);
        drinksTotalPricePanel = new DrinksTotalPricePanel(this.pizzeria);
        comandaTotalPricePanel = new ComandaTotalPricePanel(this.pizzeria);
        clientDetailsPanel = new ClientDetailsPanel(this.pizzeria);
        
        //ScrollPane per l'elenco delle pizze
        JScrollPane pizzasScrollPane = new JScrollPane(pizzasOrderedPanel);
        //Settiamo che si vede sempre la scrollbar verticale così che non ci sia l'effetto "saltino" quando essa compare
        pizzasScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //Imposto la dimensione per pizzaScrollPane. Per l'asse X va bene la dimensione del pannello genitore.
        //Per l'asse Y tengo conto di questa proporzione -> 28 : 100 = 10 : x -> x = (100*10)/28
        //Perché tutto ciò? Perché senno quando aggiungo le pizze queste vanno a "schiacciare" la parte di interfaccia che sta sotto: invece io voglio
        //che dopo un tot di pizze entri in gioco lo scrollPane...
        Dimension preferredDimension = this.getSize();
        preferredDimension.height = preferredDimension.height*((100*10)/28);
        pizzasScrollPane.setPreferredSize(preferredDimension);
        //pizzasScrollPane.setMaximumSize(preferredDimension);  //NON FUNZIONA IN QUESTO CASO
        
//        this.setLayout(new GridLayout(3, 1));
//        
//        this.add(pizzasScrollPane);
//        this.add(drinksOrderedPanel);
//        this.add(clientDetailsPanel);
        
        //Utilizzo un GridBagLayout così da poter dare un "peso" in termini di grandezza ad ogni riga della tabella
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints lim = new GridBagConstraints();
        this.setLayout(layout);
        
        //Prima riga: l'elenco delle pizze.
        //Ha peso 10: vuol dire che tra lo spazio ripartito per ogni riga se ne prende un bel po
        lim.gridx = 0;
        lim.gridy = 0;
        lim.weightx = 1;
        lim.weighty = 10;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(pizzasScrollPane, lim);
        this.add(pizzasScrollPane);
        
        //Seconda riga: il totale delle pizze
        //Ha peso 2: vuol dire che tra lo spazio ripartito per ogni riga ne prende poco, come noi vogliamo che sia...
        lim.gridx = 0;
        lim.gridy = 1;
        lim.weightx = 1;
        lim.weighty = 1;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(pizzasTotalPricePanel, lim);
        this.add(pizzasTotalPricePanel);
        
        //Terza riga: l'elenco delle bevande
        lim.gridx = 0;
        lim.gridy = 2;
        lim.weightx = 1;
        lim.weighty = 10;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(drinksOrderedPanel, lim);
        this.add(drinksOrderedPanel);
        
        //Quarta riga: il totale delle bevande
        lim.gridx = 0;
        lim.gridy = 3;
        lim.weightx = 1;
        lim.weighty = 2;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(drinksTotalPricePanel, lim);
        this.add(drinksTotalPricePanel);
        
        //Quinta riga: il totale delle bevande
        lim.gridx = 0;
        lim.gridy = 4;
        lim.weightx = 1;
        lim.weighty = 1;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(comandaTotalPricePanel, lim);
        this.add(comandaTotalPricePanel);
        
        //Sesta riga: i dettagli del cliente
        lim.gridx = 0;
        lim.gridy = 5;
        lim.weightx = 1;
        lim.weighty = 6;
        lim.fill = GridBagConstraints.BOTH;
        layout.setConstraints(clientDetailsPanel, lim);
        this.add(clientDetailsPanel);
        
    }

    @Override
    public void update(int selectedComandaIndex) {
        this.pizzasOrderedPanel.update(selectedComandaIndex);
        this.pizzasTotalPricePanel.update(selectedComandaIndex);
        this.drinksOrderedPanel.update(selectedComandaIndex);
        this.drinksTotalPricePanel.update(selectedComandaIndex);
        this.comandaTotalPricePanel.update(selectedComandaIndex);
        this.clientDetailsPanel.update(selectedComandaIndex);
    }
    
    
}