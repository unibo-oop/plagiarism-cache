package laterunner.graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Pictures is a class that defines some methods to get graphic resources.
 *
 */
public interface Pictures {

    /**
     * Returns the ImageIcon of the desired picture.
     * 
     * @param icon
     *          enum that identifies the image
     * @return
     *          the ImageIcon of the picture
     */
    ImageIcon getIcon(Icons icon);

    /**
     * Returns the Image of the desired picture.
     * @param icon
     *          enum that identifies the image
     * @return
     *          the Image of the picture
     */
    Image getImage(Icons icon);

}
