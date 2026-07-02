package it.unibo.isaccoop.model.action;

import java.util.Optional;

import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * HitStrategy interface.
 * */
public interface HitStrategy {

    /**
     * Method to perform hit action according to the strategy.
     * @param direction the direction in which i fire the shot
     * @param caller
     * */
    void hit(Optional<Vector2D> direction, MapElement caller);

}
