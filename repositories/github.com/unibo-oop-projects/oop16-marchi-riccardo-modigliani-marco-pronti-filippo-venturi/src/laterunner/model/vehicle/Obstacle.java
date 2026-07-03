package laterunner.model.vehicle;

import laterunner.model.collisions.BoundingBox;
import laterunner.model.collisions.ObstacleBoundingBox;
import laterunner.physics.P2d;
import laterunner.physics.S2d;

/**
 * Game obstacle.
 */
public class Obstacle extends Vehicle {

    private static final int CAR_MALUS = 1000;
    private static final int CAR_LIFE_DAMAGE = 1;
    private static final int BUS_MALUS = 3000;
    private static final int BUS_LIFE_DAMAGE = 3;
    private static final int MOTORBIKE_MALUS = 2000;
    private static final int MOTORBIKE_LIFE_DAMAGE = 0;

    private int malus;
    private int lifeDamage;
    private BoundingBox bBox;

    /**
     * Istantiates a new Obstacle.
     * 
     * @param veh
     *          obstacle's type
     * @param position
     *          obstacle starting position
     * @param speed
     *          obstacle starting speed
     */
    public Obstacle(final Vehicles veh, final P2d position, final S2d speed) {
        super(position, speed, veh);

        switch(super.getType()) {
        case OBSTACLE_CAR:
            this.malus = CAR_MALUS;
            this.lifeDamage = CAR_LIFE_DAMAGE;
            this.bBox = new ObstacleBoundingBox(this.getCurrentPos().getX(), super.getType());
            break;
        case BUS:
            this.malus = BUS_MALUS;
            this.lifeDamage = BUS_LIFE_DAMAGE;
            this.bBox = new ObstacleBoundingBox(this.getCurrentPos().getX(), super.getType());
            break;
        case MOTORBIKE:
            this.malus = MOTORBIKE_MALUS;
            this.lifeDamage = MOTORBIKE_LIFE_DAMAGE;
            this.bBox = new ObstacleBoundingBox(this.getCurrentPos().getX(), this.getType());
            break;
        default: throw new IllegalArgumentException("Obstacle with user car feature; nice try.");
        }
    }

    /**
     * Returns obstacle's malus.
     * 
     * @return
     *          obstacle's malus
     */
    public int getMalus() {
        return this.malus;
    }

    /**
     * Returns obstacle's life damage.
     * 
     * @return
     *          obstacle's life damage
     */
    public int getLifeDamage() {
        return this.lifeDamage;
    }

    /**
     * Returns obstacle's bounding box.
     * 
     * @return
     *          obstacle's bounding box
     */
    public BoundingBox getBBox() {
        return this.bBox;
    }

    @Override
    public void setSpd(final S2d speed) {
        super.setCheckedSpeed(new S2d(0, speed.getY()));
    }
}
