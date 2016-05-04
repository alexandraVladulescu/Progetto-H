/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o.input.ingredient_reader;

import data.Ingredient;
import java.io.IOException;

/**
 *
 * @author Markenos
 */
public interface IngredientReaderFactory {

    abstract Ingredient getNextIngredient() throws IOException;

    abstract boolean hasNextIngredient() throws IOException;
}
