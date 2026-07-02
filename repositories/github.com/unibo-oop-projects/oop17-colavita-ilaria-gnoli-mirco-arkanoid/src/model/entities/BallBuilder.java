package model.entities;

import javafx.util.Pair;
import model.ModelCostant;

/**
 * Classe builder per {@link Ball}.
 * 
 * @author Gnoli Mirco
 *
 */
public class BallBuilder {

    private Pair<Integer, Integer> position;
    private int radius = ModelCostant.DEFAULT_BALL_RADIUS;
    private int speed = ModelCostant.DEFAULT_SPEED;
    private int angle = ModelCostant.DEFAULT_ANGLE;
    private BallType type;

    /**
     * Setta la posizione.
     * @param x - int 
     * @param y - int
     * @return this
     */
    public BallBuilder position(final int x, final int y) {
        this.position = new Pair<>(x, y);
        return this;
    }

    /**
     * Setta il raggio della pallina.
     * @param radius - int
     * @return this
     */
    public BallBuilder radius(final int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * Setta la velocità della pallina.
     * @param speed - int
     * @return this
     */
    public BallBuilder speed(final int speed) {
        this.speed = speed;
        return this;
    }

    /**
     * Setta l'angolo di direzione.
     * 
     * @param angle - int
     * @return this
     */
    public BallBuilder angle(final int angle) {
        this.angle = angle;
        return this;
    }

    /**
     * Setta la tipologia di pallina a scelta fra {@link BallType}.
     * 
     * @param type - {@link BallType}
     * @return this
     */
    public BallBuilder type(final BallType type) {
        this.type = type;
        return this;
    }

    /**
     * Costruisce la pallina con i parametri settati.
     * @return {@link Ball}
     */
    public Ball build() {
        return new Ball(position.getKey(), position.getValue(), radius, speed, angle, type);
    }


}
