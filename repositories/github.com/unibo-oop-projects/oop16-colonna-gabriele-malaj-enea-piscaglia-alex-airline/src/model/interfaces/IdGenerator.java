package model.interfaces;

/**
 * 
 * Represents the identifier generator.
 */
public interface IdGenerator {

    /**
     * 
     * @return the identifier letter
     */
    char getLetter();

    /**
     * 
     * @return the identifier number
     */
    int getCounter();

    /**
     * Sets the letter from which the identifiers begin to be generated.
     * 
     * @param c    the letter from which the identifiers begin to be generated
     */
    void setInitialLetter(char c);

    /**
     * Sets the number from which the identifiers begin to be generated.
     * 
     * @param n    the number from which the identifiers begin to be generated
     */
    void setInitialNumber(int n);

    /**
     * 
     * @return the identifier
     */
    String generate();

}