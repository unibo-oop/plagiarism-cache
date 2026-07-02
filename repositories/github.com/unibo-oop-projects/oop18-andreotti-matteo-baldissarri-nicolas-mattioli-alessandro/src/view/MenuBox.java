package view;

import java.awt.Toolkit;

import javafx.scene.layout.VBox;

/**
 * 
 * Redefine class VBox.
 *
 */
public class MenuBox extends VBox {

    private static final double TRANSLATEX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10;
    private static final double TRANSLATEY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;

    /**
     * constructor.
     */
    public MenuBox() {
        super();
        setTranslateX(TRANSLATEX);
        setTranslateY(TRANSLATEY);
    }

    /**
     * 
     * @param x Horizontal position of Menu box.
     * @param y Vertical position of Menu box.
     */
    public MenuBox(final double x, final double y) {
        super();
        setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / x);
        setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / y);
    }

}
