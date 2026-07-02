package model;

import java.util.List;

import javafx.scene.shape.Rectangle;
import utility.Pair;

/**
 * The player class
 *
 * @author denis
 */
public class Player implements Entity {


    public static final double START_X = 180;
    public static final double START_Y = 500;
    private static final int CAR_WIDTH = 55;
    private static final int CAR_HEIGHT = 110;
    private static final int WORLD_HEIGHT = 550;
    private static final int WORLD_WIDTH = 285;
    private static final int HEALTH_BUF = 50;

    private int id;
    private double health = 100.00;
    private int shield = 0;

    private Pair<Double, Double> position;
    private Pair<Integer, Integer> speed;
    private Rectangle hitbox;

    public Player() {
        this.id = 1;
        this.position = new Pair<>(START_X, START_Y);
        this.speed = new Pair<>(0, 0);
        this.hitbox = new Rectangle(CAR_WIDTH, CAR_HEIGHT);
    }

    /**
     * @return health of the player
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Reset the position of the player
     */
    public void resetPosition() {
        this.position.setX(START_X);
        this.position.setY(START_Y);
    }

    /**
     * @return the speed of the player
     */
    public Pair<Integer, Integer> getSpeed() {
        return speed;
    }

    /**
     * @param speed: set the player's speed
     */
    public void setSpeed(Pair<Integer, Integer> speed) {
        this.speed = speed;
    }

    /**
     * @param health that will modify player's health
     */
    public void setHealth(double health) {
        this.health = Math.max(0, health);
    }

    /**
     * The method that will update the player's position and check the outbound of
     * the road
     */
    public void updatePosition() {
        if (position.getX() + this.speed.getX() < 60) {
            this.position.setX(60.0);
        } else {
            this.position.setX(Math.min(WORLD_WIDTH, position.getX() + this.speed.getX()));
        }

        if (position.getY() + this.speed.getY() < 10) {
            this.position.setY(10.0);
        } else {

            this.position.setY(Math.min(WORLD_HEIGHT, position.getY() + this.speed.getY()));
        }
    }

    /**
     * Method that will check the collision in the stage
     *
     * @param entities: List of all entities in the stage
     */
    public void collision(final List<Entity> entities) {
        for (Entity entity : entities) {

            if (this.hitbox.getBoundsInParent().intersects(entity.getHitbox().getBoundsInParent())) {
                if (entity instanceof PowerUp) {
                    switch (entity.getType()) {
                        case SHIELD:
                            int shield = this.getShield() + 1;
                            this.setShield(shield);
                            break;
                        case LIFE:
                            double health = this.getHealth() + HEALTH_BUF;
                            this.setHealth(health);
                            break;
                        default:
                            break;
                    }
                    ((PowerUp) entity).setOut();

                } else if (entity instanceof Enemy) {
                    if (this.getShield() >= 1) {
                        int shield = this.getShield() - 1;
                        this.setShield(shield);
                    } else {
                        double health = this.getHealth() - ((Enemy) entity).getDamage();
                        this.setHealth(health);
                    }
                    ((Enemy) entity).setOut();
                }

            }
        }

    }

    /**
     * @param shield: set shield
     */
    private void setShield(final int shield) {
        this.shield = Math.max(0, shield);
    }

    /**
     * Get the player's position
     */
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    /**
     * @param pair: Set the player's position
     */
    public void setPosition(final Pair<Double, Double> pair) {
        this.position = pair;
    }

    /**
     * Getter of the hitbox rectangle of player's car
     */
    @Override
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    /**
     * Return the player's type, util for the ImageLoader
     */
    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Get the player's id, util for future immagine implementation
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * @return shield
     */
    public int getShield() {
        return this.shield;
    }

    /**
     * Method that will update player's position and hitbox position
     */
    @Override
    public void update() {
        if (position.getX() + this.speed.getX() < 60) {
            this.position.setX(60.0);
        } else {
            this.position.setX(Math.min(WORLD_WIDTH, position.getX() + this.speed.getX()));
        }

        if (position.getY() + this.speed.getY() < 10) {
            this.position.setY(10.0);
        } else {

            this.position.setY(Math.min(WORLD_HEIGHT, position.getY() + this.speed.getY()));
        }
        this.hitbox.setLayoutX(this.position.getX());
        this.hitbox.setLayoutY(this.position.getY());
    }

}
