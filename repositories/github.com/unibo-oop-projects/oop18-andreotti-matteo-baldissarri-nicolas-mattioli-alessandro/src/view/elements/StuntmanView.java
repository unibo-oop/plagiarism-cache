package view.elements;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Models stuntman for the view.
 *
 */
public class StuntmanView extends AbstractElementView {

    private static final double DIMENSION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 18;
    private static final double DIMENSION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.5;

    /**
     * Contructor.
     */
    public StuntmanView() {
        super(new Dimension2D(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        getSprites().addAll(Arrays.asList(
                new Image("stuntman" + File.separator + "stuntman00.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman01.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman02.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman03.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman04.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman05.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman06.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman07.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true),
                new Image("stuntman" + File.separator + "stuntman08.png", DIMENSION_WIDTH, DIMENSION_HEIGHT, false,
                        true)));
    }

}
