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
public class BonusView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.4;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * Contructor.
     */
    public BonusView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("entity" + File.separator + "bonus" + File.separator + "hp.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "bonus" + File.separator + "points.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true)));
    }
}
