/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o_V1;

import java.io.FileNotFoundException;

/**
 *
 * @author Francesco
 */
public class MenuDrinkLoader {

    public DrinkReaderFactory getFilePizzaReader(String path, FormatType type) throws FileNotFoundException {
        DrinkReaderFactory fileDrinkReader;
        switch (type) {
            case TXT:
                fileDrinkReader = new DrinkTxtReader(path);
                break;
            case XML:
                fileDrinkReader = new DrinkXmlReader(path);
                break;
            default:
                fileDrinkReader = null;
        }
        return fileDrinkReader;
    }
}
