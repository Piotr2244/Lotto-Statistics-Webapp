/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Exception class Lotto Statistics
 *
 * @author Piotr Benio
 * @version 2.0
 */
public class LottoBaseException extends Exception {

    /**
     * Exception method sending error message
     *
     * @param errorMessage is the message with error explanation
     */
    public LottoBaseException(String errorMessage) {
        super(errorMessage);
    }

}
