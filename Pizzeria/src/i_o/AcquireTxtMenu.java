/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o;
import data.*;

import java.io.FileNotFoundException;

/**
 *
 * @author Francesco
 */
public class AcquireTxtMenu extends AcquireMenu{

    public AcquireTxtMenu() {
        super();
    }
    @Override
     public FileProductReader getFileProductReader(String type) throws FileNotFoundException {
         FileProductReader fileProductReader ;
        switch (type) {
            case "pizza":
                fileProductReader = new PizzaTxtReader();
                break;
            case "drink":
                fileProductReader = new DrinkTxtReader();
                break;
            default : fileProductReader = null;
        }
        return fileProductReader;
    }
}
