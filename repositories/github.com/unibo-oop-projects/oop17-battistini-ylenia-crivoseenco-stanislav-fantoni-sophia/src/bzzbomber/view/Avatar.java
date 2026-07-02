package bzzbomber.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import bzzbomber.view.menu.GUIFactory;
import bzzbomber.view.menu.GUIFactoryImpl;

/**
 * This class implements an model of object @Avatar .
 *
 */
public class Avatar {

    private static final int HEIGHTIMAGE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.33);
    private static final int WIDTHIMAGE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.18);

    private final ImageIcon card;
    private final String name;

    /**
     * This is the constructor of the class @Avatar .
     * 
     * @param image
     *            The path of the resources to draw in the graphic interface.
     * @param name
     *            The name of the @BzzBomber .
     */
    public Avatar(final String image, final String name) {
        final GUIFactory factory = new GUIFactoryImpl();
        this.card = new ImageIcon(factory.createPathImage(image));
        final Image scaledImage = this.card.getImage().getScaledInstance(WIDTHIMAGE, HEIGHTIMAGE, Image.SCALE_DEFAULT);
        this.card.setImage(scaledImage);
        this.name = name;
    }

    /**
     * Getter of Avatar's Card.
     * 
     * @return The @ImageIcon of the @Avatar .
     */

    public ImageIcon getCard() {
        return this.card;
    }

    /**
     * Getter of Avatar's name.
     * 
     * @return The @String of name of the @Avatar .
     */
    public String getName() {
        return this.name;
    }

}
