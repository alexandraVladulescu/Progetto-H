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
public abstract class MenuLoader {

    ArrayList<Product> productList = new ArrayList<>();

    public MenuLoader() {

    }

    public void fillProductList(String type) throws FileNotFoundException, IOException {

        ProductReaderFactory fileProductReader;
        Product product;
        fileProductReader = getFileProductReader(type);
        while (fileProductReader.hasNextProduct()) {
            productList.add(fileProductReader.getNextProduct());
        }
    }

    public ArrayList<Product> getMenu() {
        return productList;
    }

    abstract ProductReaderFactory getFileProductReader(String type) throws FileNotFoundException;

}
