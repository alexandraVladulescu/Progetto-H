/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.ingredient_reader;

import data.Ingredient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public class IngredientTxtReader implements IngredientReaderFactory {

    private FileReader f;
    private BufferedReader buffer;
    private String line = "";

    public IngredientTxtReader(String path) throws FileNotFoundException {
        f = new FileReader(path);
        buffer = new BufferedReader(f);

    }

    @Override
    public Ingredient getNextIngredient() {
        String[] array ;
       array = this.line.split("\t");
        System.out.println(array[0]);
       return new Ingredient(array[0], Double.parseDouble(array[1]));
        
    }

    @Override
    public boolean hasNextIngredient() throws IOException {
        if ((line = buffer.readLine()) != null) {
            setLine(line);// Setto la linea che verrà utilizzata da getNextProduct
            return true;
        } else {
            buffer.close();
            f.close();
            return false;
        }
    }

    public void setLine(String line) {
        this.line = line;
    }

}
