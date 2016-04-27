/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o_V1;

import data.Drink;

import java.io.IOException;

/**
 *
 * @author Francesco
 */
interface DrinkReaderFactory {
    abstract Drink getNextProduct() throws IOException;
    abstract boolean hasNextProduct() throws IOException;
    
}
