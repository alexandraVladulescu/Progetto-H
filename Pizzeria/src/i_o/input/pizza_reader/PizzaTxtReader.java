/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.pizza_reader;


import data.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public class PizzaTxtReader implements PizzaReaderFactory {

    private FileReader f;
    private BufferedReader buffer;
    private String line = "";

    public PizzaTxtReader(String path) throws FileNotFoundException {
        f = new FileReader(path);
        buffer = new BufferedReader(f);

    }

    @Override
    public DescriptionPizza getNextProduct() throws IOException {
        String[] firstLine;
        String[] secondLine;
        firstLine = line.split("\t");
        secondLine = buffer.readLine().split("\t");// legge la seconda riga per gli ingredienti
        DescriptionPizza DPizza = new DescriptionPizza(firstLine[0], Double.parseDouble(firstLine[1]));
        for (String ingredientLine : secondLine) {
            DPizza.addIngredient(new Ingredient(ingredientLine));
        }
       
        return DPizza;

    }

    @Override
    public boolean hasNextProduct() throws IOException {
        if ((line = buffer.readLine()) != null) {
            setLine(line);// Setto la linea che verrà utilizzata da getNextProduct
            return true;
        } else {
            buffer.close();
            f.close();
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setLine(String line) {
        this.line = line;
    }

}
