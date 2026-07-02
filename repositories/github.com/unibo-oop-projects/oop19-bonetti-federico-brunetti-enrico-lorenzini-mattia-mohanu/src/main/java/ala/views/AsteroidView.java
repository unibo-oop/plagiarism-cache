package ala.views;

import ala.models.AsteroidModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * AsteroidView class.
 * 
 */
public final class AsteroidView extends DynamicGameObjectView {
    //Attributes:
    private static final Image IMG_ASTEROID = new Image(AsteroidView.class.getResource("/Asteroid.gif").toExternalForm());
    private AsteroidModel asteroidModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param asteroidModel
     * 
     */
    public AsteroidView(final Pane layer, final AsteroidModel asteroidModel) {
        super(layer, IMG_ASTEROID, asteroidModel);
        this.asteroidModel = asteroidModel;
    }

    //Getters&Setters:
    public AsteroidModel getAsteroidModel() {
        return asteroidModel;
    }

    public void setAsteroidModel(final AsteroidModel asteroidModel) {
        this.asteroidModel = asteroidModel;
    }

    public static Image getImgAsteroid() {
        return IMG_ASTEROID;
    }
}
