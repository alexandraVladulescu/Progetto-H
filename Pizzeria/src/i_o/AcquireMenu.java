/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o;
import data.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public abstract class AcquireMenu {

  
    ArrayList<Product> list = new ArrayList<>();

    public AcquireMenu() {
        
    }

    public void parseFile(String path, String type) throws FileNotFoundException, IOException {

        FileProductReader fileProductReader;
        Product product;
        fileProductReader = getFileProductReader(path, type);
        while (fileProductReader.hasNextProduct()) {
            list.add(fileProductReader.getNextProduct());
        }
    }

    public ArrayList<Product> getMenu() {
        return list;
    }
    abstract FileProductReader getFileProductReader(String path,String type) throws FileNotFoundException;

}
