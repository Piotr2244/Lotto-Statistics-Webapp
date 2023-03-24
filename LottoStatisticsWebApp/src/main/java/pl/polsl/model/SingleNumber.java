/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Class for single lotto number from 1 to 40
 *
 * @author Piotr Benio
 * @version 1.0
 */
public class SingleNumber {

    /**
     * Value of lotto number from 1 to 40.
     */
    private int value;

    /**
     * Returns the value of the lotto number.
     *
     * @return Value is a value of the lotto number.
     */
    public int getNumber() {
        return value;
    }

    /**
     * Sets the value of the lotto number
     *
     * @param number is a new value of the lotto number
     */
    public void setNumber(int number) {
        this.value = number;
    }
}
