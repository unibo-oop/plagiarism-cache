package view;

import java.awt.Toolkit;
import java.io.File;

import javafx.scene.image.ImageView;

/**
 * 
 * Class for redefine ImageView as a title of the game.
 *
 */
public class MenuTitle extends ImageView {

    private static final double TITLESCALEX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2953;
    private static final double TITLESCALEY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1661.5;

    /**
     * 
     * @param x x position of title
     * @param y y position of title
     */
    public MenuTitle(final double x, final double y) {
        super("menu" + File.separator + "logo.png");
        setScaleX(TITLESCALEX);
        setScaleY(TITLESCALEY);
        setTranslateX(-(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / x));
        setTranslateY(-(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / y));
    }
}
