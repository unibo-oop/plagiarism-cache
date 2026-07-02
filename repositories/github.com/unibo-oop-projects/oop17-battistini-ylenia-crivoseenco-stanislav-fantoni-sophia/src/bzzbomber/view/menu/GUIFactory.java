package bzzbomber.view.menu;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Interface of @GUIFactoryImpl for view's elements.
 *
 */
public interface GUIFactory {

    /**
     * Create a basic frame with our setting.
     * 
     * @return The frame.
     */
    JFrame createBasicFrame();

    /**
     * Create a @JPanel with image and color.
     * 
     * @param image
     *            The path of the resources to load.
     * @param top
     *            The insect from the top.
     * @param position
     *            The insect from the left.
     * @param distance
     *            The insect from the bottom.
     * @param right
     *            The insect from the right.
     * @param colore
     *            Color for paint the JPanel.
     * 
     * @return JPanel.
     */

    JPanel createTitleButton(String image, int top, int position, int distance, int right, Color colore);

    /**
     * Create a @JButton with an @Icon .
     * 
     * @param image
     *            The path of the resources to load.
     * @param width
     *            The width of the button.
     * @param height
     *            The height of the button.
     * @return The button.
     */

    JButton createImageButton(String image, int width, int height);

    /**
     * Create a @JButton with "back to menu" text.
     * 
     * @return The button.
     */
    JButton createBackButton();

    /**
     * Create a @JButton with scaled image.
     * @param name 
     *            Is the name of button
     * @param pathImage
     *          Is the name of image 
     * @return JButton
     */
    JButton createButton(String name, String pathImage);

    /**
     * This method create a Load Image from path.
     * 
     * @param nameImage
     *            Is the name of image
     * @return an Image
     */
    Image createPathImage(String nameImage);

}
