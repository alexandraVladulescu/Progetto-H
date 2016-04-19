/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Francesco
 */
public class PizzaNotFoundInMenuException extends Exception {

    public PizzaNotFoundInMenuException(String message) {
        super(message);
        printStackTrace();
    }
    
}
