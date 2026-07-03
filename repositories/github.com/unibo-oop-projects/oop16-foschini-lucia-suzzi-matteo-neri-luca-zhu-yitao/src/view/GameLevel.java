package view;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.pkglevels.Pipe;

/**
 * Interface for the game level.
 * 
 * 
 *
 */
public interface GameLevel {
    /**
     * Method used to set the start and finish button.
     * 
     * @param button
     *            button to set
     * @param img
     *            img to change
     * @param lines
     *            grid x coordinate
     * @param columns
     *            grid y coordinate
     * @param gbc
     *            gridbagconstraint
     * @param p
     *            panel
     * @param newX
     *            new width
     * @param newY
     *            new height
     */
    void setStartAndFinish(JButton button, ImageIcon img, int lines, int columns, GridBagConstraints gbc, JPanel p,
            int newX, int newY);

    /**
     * Method to set the current pipe list.
     * 
     */
    void loadImages();

    /**
     * Method used to close the current level window.
     * 
     */
    void closeWindow();

    /**
     * Method used to get the pipe.
     * 
     * @param pipeType
     *            Pipe's type
     * @return the selected pipe
     */

    Pipe getTube(String pipeType);

    /**
     * Method used to update the button icon.
     * 
     * @param b
     *            button
     * @param i
     *            new image
     */

    void updateBtnIcon(JButton b, ImageIcon i);

    /**
     * Method that returns the finish button.
     * 
     * @return the finish button requested
     */
    JButton getFinish();

    /**
     * Used to set the closing window behaviour.
     * 
     */
    void setWinListner();

}
