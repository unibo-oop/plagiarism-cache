package view.elements;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Model vase for the view.
 *
 */
public class VaseView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.5;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * Contructor.
     */
    public VaseView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("entity" + File.separator + "vase" + File.separator + "vase00.png", DIMENSION_WIDTH / 2,
                        DIMENSION_HEIGHT / 2, false, true),
                new Image("entity" + File.separator + "vase" + File.separator + "vase01.png", DIMENSION_WIDTH * 2,
                        DIMENSION_HEIGHT, false, true)));
    }
}
