package controller;

/**
 * It is the interface used to save and read informations on files.
 *
 * @param <X>
 *            the generic type of elements to read.
 */
public interface SaveAndRead<X> {
    /**
     * It saves informations in the file "fileName".
     */
    void save();

    /**
     * It read informations from the file.
     *
     * @return a list of X, used to return the informations read to the program.
     */
    X read();

    /**
     * It resets informations saved in the file.
     */
    void reset();

}
