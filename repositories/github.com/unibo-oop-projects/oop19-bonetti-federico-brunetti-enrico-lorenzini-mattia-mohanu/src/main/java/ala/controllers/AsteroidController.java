package ala.controllers;

import ala.models.AsteroidModel;
import ala.views.AsteroidView;

/**
 * the Asteroid's controller class.
 * 
 */
public final class AsteroidController extends DynamicGameObjectController {

    //Attributes:
    private AsteroidModel asteroidModel;
    private AsteroidView asteroidView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param asteroidModel
     * @param asteroidView
     * 
     */
    public AsteroidController(final AsteroidModel asteroidModel, final AsteroidView asteroidView) {
        super(asteroidModel, asteroidView);
        this.asteroidModel = asteroidModel;
        this.asteroidView = asteroidView;
    }

    //Getters & Setter:
    public AsteroidModel getAsteroidModel() {
        return asteroidModel;
    }

    public void setAsteroidModel(final AsteroidModel asteroidModel) {
        this.asteroidModel = asteroidModel;
    }

    public AsteroidView getAsteroidView() {
        return asteroidView;
    }

    public void setAsteroidView(final AsteroidView asteroidView) {
        this.asteroidView = asteroidView;
    }
}
