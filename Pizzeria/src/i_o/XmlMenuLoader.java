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
public class XmlMenuLoader extends MenuLoader {

    @Override
   
       public ProductReaderFactory getFileProductReader(String type) throws FileNotFoundException {
         ProductReaderFactory fileProductReader ;
        switch (type) {
            case "pizza":
                fileProductReader = new PizzeXmlReader();
                break;
            case "drink":
                fileProductReader = new DrinkXmlReader();
                break;
            default : fileProductReader = null;
        }
        return fileProductReader;
    }
    }
    

