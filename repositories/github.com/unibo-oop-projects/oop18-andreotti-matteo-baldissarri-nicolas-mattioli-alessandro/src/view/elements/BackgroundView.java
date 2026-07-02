package view.elements;

import java.awt.Toolkit;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Models Background for the view.
 *
 */
public class BackgroundView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * Contructor.
     */
    public BackgroundView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites()
                .addAll(Arrays.asList(new Image("BackgroundFloor.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false, true),
                        new Image("BackgroundSky.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false, true)));
    }

}
