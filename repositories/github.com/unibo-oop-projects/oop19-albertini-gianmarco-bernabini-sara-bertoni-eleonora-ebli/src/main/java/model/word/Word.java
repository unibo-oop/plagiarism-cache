package model.word;

import javafx.util.Pair;

public interface Word {

    /**
     * @return the first letter: word's char at index 0.
     */
    char getFirstLetter();

    /**
     * @param firstLetter
     */
    void setFirstLetter(char firstLetter);

    /**
     * @return a string with the word.
     */
    String getWord();

    /**
     * @param word
     */
    void setWord(String word);

    /**
     * @return true if the word is active (selected).
     */
    boolean isActive();

    /**
     * @param isActive
     */
    void setActive(boolean isActive);

    /**
     * @return the word's length.
     */
    int getLength();

    /**
     * @param length
     */
    void setLength(int length);

    /**
     * @return how many word's letters were typed.
     */
    int getTyped();

    /**
     * @param typed
     */
    void setTyped(int typed);

    /**
     * @return word's speed
     */
    double getSpeed();

    /**
     * @param speed
     */
    void setSpeed(double speed);

    /**
     * @return word's position.
     */
    Pair<Double, Double> getPosition();

    /**
     * @param pos
     */
    void setPosition(Pair<Double, Double> pos);

    /**
     * @return the second word's coordinate
     */
    double wordGetY();

    /**
     * 
     * @return the first word's coordinate
     */
    double wordGetX();

    /**
     * 
     * @param index
     * @return the char at the index given by param
     *
     */
    char getCharAt(int index);
}
