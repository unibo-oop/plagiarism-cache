package model;

import javafx.scene.shape.Rectangle;
import utility.Pair;


/**
 * This class allows the creation of enemies object
 *
 * @author Roberto Di Lena
 */
public class Enemy implements Entity {


    private static final int CAR_DAMAGE = 50;
    private static final int MOTOR_DAMAGE = 20;
    private static final int TRUCK_DAMAGE = 80;
    private static final double CAR_WIDTH = 55;
    private static final double CAR_HEIGHT = 110;
    private static final double TRUCK_WIDTH = 75;
    private static final double TRUCK_HEIGHT = 200;
    private static final double MOTOR_WIDTH = 40;
    private static final double MOTOR_HEIGHT = 70;
    private static final double WINDOWS_HEIGHT = 700;
    private double speed;
    private int damage;
    private int id;
    private EntityType type;
    private Rectangle hitbox;
    private Pair<Double, Double> position = null;

    public Enemy(final EntityType type, final double x, final double speed, final int id) {

        this.speed = speed;
        this.id = id;
        this.type = type;
        switch (type) {
            case TRUCK:
                this.damage = TRUCK_DAMAGE;
                this.hitbox = new Rectangle(TRUCK_WIDTH, TRUCK_HEIGHT);
                this.position = new Pair<>(x - (TRUCK_WIDTH / 2), 0 - TRUCK_HEIGHT);
                break;
            case MOTORBIKE:
                this.damage = MOTOR_DAMAGE;
                this.hitbox = new Rectangle(MOTOR_WIDTH, MOTOR_HEIGHT);
                this.position = new Pair<>(x - (MOTOR_WIDTH / 2), 0 - MOTOR_HEIGHT);
                break;
            case CAR:
                this.damage = CAR_DAMAGE;
                this.hitbox = new Rectangle(CAR_WIDTH, CAR_HEIGHT);
                this.position = new Pair<>(x - (CAR_WIDTH / 2), 0 - CAR_HEIGHT);
                break;
		default:
			break;
        }

    }

    public Enemy(final EntityType type) {
        switch (type) {
            case TRUCK:
                this.damage = TRUCK_DAMAGE;
                break;
            case MOTORBIKE:
                this.damage = MOTOR_DAMAGE;
                break;
            case CAR:
                this.damage = CAR_DAMAGE;
                break;
		default:
			break;
        }
    }

    public Enemy() {
    }

    /**
     * update enemy position by speed
     */
    public void update() {

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
     * @return id
     */
    public int getId() {

        return this.id;
    }

    /**
     * @return hitbox
     */
    public Rectangle getHitbox() {

        return this.hitbox;
    }

    /**
     * @return type
     */
    public EntityType getType() {

        return this.type;
    }

    /**
     * @return damage
     */
    public int getDamage() {

        return this.damage;
    }

    /**
     * set position out of the map
     */
    public void setOut() {

        this.position.setY(WINDOWS_HEIGHT);
        this.hitbox.setLayoutX(this.position.getX());
        this.hitbox.setLayoutY(this.position.getY());
    }
}