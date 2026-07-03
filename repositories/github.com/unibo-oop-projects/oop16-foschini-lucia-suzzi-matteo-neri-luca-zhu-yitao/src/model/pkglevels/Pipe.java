package model.pkglevels;

import javax.swing.ImageIcon;

/**
 * Interface for the Pipe class.
 * 
 * 
 *
 */
public interface Pipe {
    /**
     * Method used to change the pipe image and set it to the water flow
     * sequence.
     * 
     * @param nextIn
     *            next input used to chose the image
     * @param dir
     *            integer sued to chose the correct list of images to use
     * @param numb
     *            number of image which must be selected
     */
    void changeIconOnSolution(int nextIn, int dir, int numb);

    /**
     * Returns the pipe's image.
     * 
     * @return image
     */
    ImageIcon getImg();

    /**
     * Returns pipe's default image.
     * 
     * @return default image
     */
    ImageIcon getEmptyPipe();

    /**
     * Return the pipe direction.
     * 
     * @return direction
     */
    int getDir();

    /**
     * Get the pipe type/name.
     * 
     * @return typer
     */

    String getType();

    /**
     * Returns the next pipe in sequence.
     * 
     * @return next pipe
     */
    Pipe getNextTube();

    /**
     * Adds the next pipe to the current pipe.
     * 
     * @param t
     *            next pipe
     */

    void addPipes(Pipe t);

    /**
     * GEt the pipe input value.
     * 
     * @return pipe input
     */
    int getInput();

    /**
     * Gets the pipe output value.
     * 
     * @return pipe output
     */
    int getOutput();

    /**
     * Changes input with output and vice versa.
     */

    void switchIO();

}
