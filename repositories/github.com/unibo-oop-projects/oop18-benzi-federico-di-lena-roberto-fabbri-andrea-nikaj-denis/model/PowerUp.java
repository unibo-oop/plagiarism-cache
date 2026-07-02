package model;

import javafx.scene.shape.Rectangle;
import utility.Pair;

/**
 * This class allows the creation of PowerUP object
 *
 * @author Federico Benzi
 */

public class PowerUp implements Entity {

    private static final double POWERUP_WIDTH = 60;
    private static final double POWERUP_HEIGHT = 60;
    private final static double WINDOWS_HEIGHT = 700;
    private double speed;
    private int id;
    private Rectangle hitbox;
    private Pair<Double, Double> position = null;
    private EntityType type;

    public PowerUp(final EntityType type, final double x, final double speed, final int id) {
        this.type = type;
        this.speed = speed;
        this.id = id;
        this.hitbox = new Rectangle(POWERUP_WIDTH,POWERUP_HEIGHT);
        this.position = new Pair<>(x - (POWERUP_WIDTH / 2),0 - POWERUP_HEIGHT);
    }

    /**
     * update PowerUP position by speed
     */    public void update() {
        this.position.setY(this.position.getY() + this.speed);
        this.hitbox.setLayoutX(this.position.getX());
		this.hitbox.setLayoutY(this.position.getY());
    }

    /**
     * @return position
     */
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * return hitbox
     */
    public Rectangle getHitbox() {
        return this.hitbox;
    }
    /**
     * @return type
     */    public EntityType getType() {
        return this.type;
    }
    /**
     * set position out of the map
     */    public void setOut(){
        this.position.setY(WINDOWS_HEIGHT);
        this.hitbox.setLayoutX(this.position.getX());
        this.hitbox.setLayoutY(this.position.getY());
    }
}
