/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o;
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
public class PizzaTxtReader implements FileProductReader {

    private FileReader f;
    private BufferedReader buffer;
     private String line = "";

    public PizzaTxtReader() throws FileNotFoundException {
        f = new FileReader("./databases/pizze.txt");
        buffer = new BufferedReader(f);

    }

    @Override
    public Pizza getNextProduct() throws IOException {
        String[] array;
        array = line.split("\t");
        return new Pizza(array[0], Double.parseDouble(array[1]));

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
