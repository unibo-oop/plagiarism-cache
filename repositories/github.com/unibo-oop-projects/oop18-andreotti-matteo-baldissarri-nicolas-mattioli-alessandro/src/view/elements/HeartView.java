package view.elements;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Models heart for the view.
 *
 */
public class HeartView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 20;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 12;

    /**
     * Contructor.
     */
    public HeartView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("hud" + File.separator + "heart.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false, true)));
    }

}
