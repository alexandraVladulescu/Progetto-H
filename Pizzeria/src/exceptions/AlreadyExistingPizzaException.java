/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Viene sollevata quando si vuole aggiungere una pizza già esistente a quelle presenti nella pizzeria.
 * Per già esistenti si intende avente lo stesso nome. Quindi due pizze possono avere nomi diversi ma stessi ingredienti
 * che il programma non da nesssun errore.
 * @author Francesco
 */
public class AlreadyExistingPizzaException extends Exception {

    public AlreadyExistingPizzaException(String message) {
        super(message);
    }
    
}
