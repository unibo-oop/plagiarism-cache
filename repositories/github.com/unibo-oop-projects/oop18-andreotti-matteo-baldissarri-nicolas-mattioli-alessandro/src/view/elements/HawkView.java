package view.elements;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Model hawk for the view.
 *
 */
public class HawkView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.4;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * Contructor.
     */
    public HawkView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk00.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk01.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk02.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk03.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk04.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk05.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk06.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk07.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true),
                new Image("entity" + File.separator + "hawk" + File.separator + "hawk08.png", DIMENSION_WIDTH,
                        DIMENSION_HEIGHT, false, true)));
    }
}
