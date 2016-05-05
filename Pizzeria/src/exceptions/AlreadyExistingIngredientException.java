/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Viene sollevata quando si vuole aggiungere un ingrediente già esistente a quelli presenti nella pizzeria.
 * @author Francesco
 */
public class AlreadyExistingIngredientException extends Exception {

    public AlreadyExistingIngredientException(String message) {
        super(message);
    }
    
}
