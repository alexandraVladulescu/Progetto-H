/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o_V1;
import i_o.*;
import data.*;

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
                filePizzaReader = new PizzeXmlReader(path);
                break;
            default : filePizzaReader = null;
        }
        return filePizzaReader;
    }
     
     
     
      
}
