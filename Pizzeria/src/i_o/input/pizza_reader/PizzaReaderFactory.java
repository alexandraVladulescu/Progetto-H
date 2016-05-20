/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.pizza_reader;
import data.DescriptionPizza;
import data.Pizza;

import data.Product;
import java.io.IOException;

/**
 *
 * @author Francesco
 */
public interface PizzaReaderFactory {
    
    abstract DescriptionPizza getNextProduct() throws IOException;
    abstract boolean hasNextProduct() throws IOException;
    
}
