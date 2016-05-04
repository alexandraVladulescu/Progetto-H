/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.pizza_reader;

import data.*;
import i_o.FormatType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public class MenuPizzaLoader {

    public MenuPizzaLoader() {
       
    }
    
     public PizzaReaderFactory getFilePizzaReader(String path,FormatType type) throws FileNotFoundException {
         PizzaReaderFactory filePizzaReader ;
        switch (type) {
            case TXT :
                filePizzaReader = new PizzaTxtReader(path);
                break;
            case XML:
                filePizzaReader = new PizzaXmlReader(path);
                break;
            default : filePizzaReader = null;
        }
        return filePizzaReader;
    }
     
     
     
      
}
