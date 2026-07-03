package model.pkglevels;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * Interface for the Imageloader class.
 * 
 * 
 *
 */
public interface ImageLoader {
    /**
     * Method that loads the ImageIcons from the source folder and sets them to
     * the given dimension.
     * 
     * @param x
     *            width component
     * @param y
     *            height component
     */
    void loadImages(int x, int y);

    /**
     * Method that finds and returns the pipe associated to the given string .
     * 
     * @param tubeType
     *            Pipe's name
     * @return the selected pipe
     */
    PipeImpl getTube(String tubeType);

    /**
     * Returns the start icon.
     * 
     * @return start image
     */
    ImageIcon getStartImage();

    /**
     * Gets the finish image.
     * 
     * @return finish image
     */
    ImageIcon getFinishImage();

    /**
     * Returns the empty final image.
     * 
     * @return empty icon
     */
    ImageIcon getEmptyImage();

    /**
     * gets the solution file.
     * 
     * @return fixed solutions file
     */
    File getFixedFile();

    /**
     * gets the solution file for the custom levels.
     * 
     * @return custom solutions file
     */
    File getCustomFIle();

    /**
     * gets the login file.
     * 
     * @return login file.
     */
    File getLoginFile();

    /**
     * gets the scores file.
     * 
     * @return scores file
     */
    File getScoreFile();

    //File getTutorialFile();

}
