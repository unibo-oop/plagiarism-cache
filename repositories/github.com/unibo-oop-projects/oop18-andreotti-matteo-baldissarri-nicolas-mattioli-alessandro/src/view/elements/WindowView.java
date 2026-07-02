package view.elements;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Model window for the view.
 *
 */
public class WindowView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.4;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * Contructor.
     */
    public WindowView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("entity" + File.separator + "window" + File.separator + "windowClosed.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "window" + File.separator + "windowHalfClosed.png",
                        DIMENSION_WIDTH, DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "window" + File.separator + "windowOpen.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true)));
    }
}
