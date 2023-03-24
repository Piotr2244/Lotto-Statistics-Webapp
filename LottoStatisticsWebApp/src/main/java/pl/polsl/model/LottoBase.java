
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class stores the single lotto numbers and informations about their
 * frequency and percents
 *
 * @author Piotr Benio
 * @version 2.0
 */
public class LottoBase {

    /**
     * Stores all single number objects
     */
    private ArrayList<SingleNumber> database;

    /**
     * stores the last winning numbers set. By default, it should store 6
     * objects
     */
    private ArrayList<SingleNumber> lastWinningNumbers;

    /**
     * Stores the values from 1 to 40 as keys. The value behind every key is how
     * many times this value occured in database
     */
    private HashMap<Integer, Integer> frequency;

    /**
     * Stores the values from 1 to 40 as keys. The value behind every key
     * informs about the percentage of key number in all database elements.
     * (amount of chosen number in database divided by all numbers in
     * database*100)
     */
    private HashMap<Integer, Integer> percentages;

    /**
     * The name of database file. Before opening database procedure, this value
     * would be set as database name
     */
    private String filename;

    /**
     * Constructor. Creates all containers and sets frequency and percentage
     * hashmap key values as numbers from 1 to 40. The value behind each key is
     * 0, beacuse the main base is now empty
     */
    public LottoBase() {
        this.database = new ArrayList<>();
        this.lastWinningNumbers = new ArrayList<>();
        this.frequency = new HashMap<>();
        this.percentages = new HashMap<>();
        for (int i = 1; i <= 40; i++) {
            frequency.put(i, 0);
            percentages.put(i, 0);
        }

    }

    /**
     * Returns all elements from database container
     *
     * @return database container
     */
    public ArrayList<SingleNumber> getAllDatabaseElements() {
        return database;
    }

    /**
     * Gets the selected Single Number object from database
     *
     * @param index is an index of object which is to be returned
     * @retur chosen single number object
     */
    private SingleNumber getDatabaseElement(int index) {
        return database.get(index);
    }

    /**
     * Adds a new number to winning numbers set
     *
     * @param number is the value of the new winning number
     */
    private void addWinningNumber(SingleNumber number) {
        lastWinningNumbers.add(number);
    }

    /**
     * getter for last winning numbers ArrayList
     *
     * @return last winning numbers ArrayList
     */
    public ArrayList<SingleNumber> getWinningNumbers() {
        return lastWinningNumbers;
    }

    /**
     * After some procedures, last winning numbers may be deleted. This method
     * clones the last 6 numbers of database and puts them in last winning
     * numbers container
     */
    private void remindLastWinningNumbers() {
        lastWinningNumbers.clear();
        for (int x = 6; x > 0; x--) {
            addWinningNumber(getDatabaseElement(database.size() - x));
        }
    }

    /**
     * This method generates 6 new Single number objects and sets their values
     * Their values are random numbers from 1 to 40 After setting new number,
     * the inside loop will check if the number in set is uniqie If the number
     * is already in winning numbers set, it would be changed to a new random
     * number. Finally, all 6 winning numbers will be unique i-- line is to make
     * sure new random number isn't the same again
     */
    private void MakeNewRandomWinningNumbers() {
        lastWinningNumbers.clear();
        Random rand = new Random();
        int randomNumber;
        for (int x = 0; x < 6; x++) {
            randomNumber = rand.nextInt(40) + 1;
            SingleNumber TempModel = new SingleNumber();
            TempModel.setNumber(randomNumber);

            for (int i = 0; i < lastWinningNumbers.size(); i++) {
                if (lastWinningNumbers.get(i).getNumber() == TempModel.getNumber()) {
                    randomNumber = rand.nextInt(40) + 1;
                    TempModel.setNumber(randomNumber);
                    i--;
                }
            }
            lastWinningNumbers.add(TempModel);
        }
    }

    /**
     * This method is usually used after generating new winning numbers set The
     * new winning numbers will be put in the main database
     */
    private void updateMainBase() {
        for (int i = 0; i < lastWinningNumbers.size(); i++) {
            database.add(lastWinningNumbers.get(i));
        }
    }

    /**
     * Returns the whole frequency database
     *
     * @return the frequency container
     */
    public HashMap<Integer, Integer> getFrequency() {
        return frequency;
    }

    /**
     * gets the chosen value of Frequency container
     *
     * @param index is the key of wanted value
     * @return the value behind chosen key
     */
    private Integer getFrequencyElement(Integer index) {
        return frequency.get(index);
    }

    /**
     * This method is usually used after generating new winning numbers set The
     * frequency database will be updated, Every new winning number will
     * increment the frequency of its occurence.
     */
    private void updateFrequency() {
        Integer newValue;
        Integer key;

        for (int i = 0; i < lastWinningNumbers.size(); i++) {
            key = (lastWinningNumbers.get(i)).getNumber();
            newValue = getFrequencyElement(key);
            newValue++;
            frequency.put(key, newValue);
        }
    }

    /**
     * Returns the whole base with percentage values
     *
     * @return HashMap of percentages
     */
    public HashMap<Integer, Integer> getPercents() {
        return percentages;
    }

    /**
     * This method is usually used after updating frequency container with new
     * values. The percentage database will be updated and synchronised with
     * frequency storing container
     */
    private void updatePercents() {
        Integer percent;
        for (int i = 1; i <= frequency.size(); i++) {

            if (frequency.get(i) == 0) {
                percent = 0;
            } else {
                percent = (int) ((Double.valueOf(frequency.get(i)) / database.size()) * 100);
            }
            percentages.put(i, percent);
        }
    }

    /**
     * This method will generate new winning sets and update both database and
     * frequency base. Sets will be generated randomly and percentage container
     * will be updated.
     *
     * @param amount is an amount of new winning sets that are to be generated
     */
    public void DoNewRandomWinningSetsProcedure(int amount) throws NumberFormatException {

        int finalAmount = amount;

        if (finalAmount > 0) {
            for (int i = 0; i <= finalAmount; i++) {
                MakeNewRandomWinningNumbers();
                updateMainBase();
                updateFrequency();
                if(i==0)i++;
            }
            updatePercents();
        }
    }

    /**
     * Sets filename (database) name.
     *
     * @param name of the file that will be read
     */
    public void setFilename(String name) {
        this.filename = name;
    }

    /**
     * getter for filename
     *
     * @return filename string
     */
    public String getFilename() {
        return filename;
    }

    /**
     * This method opens the database from txt file, reads and adds new numbers
     * to database File name is set as the filename variable to make sure the
     * right file is to be opened. Reader will read the values from database and
     * put them into new winning set. The counter increments after every loaded
     * lotto value. After getting 6 of them, winning numbers set will be put in
     * database and frequency base. Then it will be cleared and the counter
     * value will be set as 0.In the end, percentage container will also be
     * updated. If any error occures during database opening,
     * FileNotFoundException will be thrown.
     *
     * @throws LottoBaseException will be thrown if the value from database will
     * not be in range between 1 or 40.
     * @throws FileNotFoundException will be thrown when file can't be found
     */
    public void getDataFromDatabase() throws LottoBaseException, FileNotFoundException {
        int counter = 0;

        File myObj = new File(filename);
        try (Scanner Reader = new Scanner(myObj)) {
           do {
                SingleNumber TempModel = new SingleNumber();
                
                TempModel.setNumber(Reader.nextInt());
                
                if (TempModel.getNumber() <= 40 && TempModel.getNumber() >= 1) {
                    addWinningNumber(TempModel);
                    counter++;
                    if (counter == 6) {
                        updateMainBase();
                        updateFrequency();
                        
                        lastWinningNumbers.clear();
                        counter = 0;
                    }
                } else {
                    throw new LottoBaseException("Numbers should be in range from 1 to 40!");
                }
            }  while (Reader.hasNextLine());
            updatePercents();
        }
        remindLastWinningNumbers();
    }

}
