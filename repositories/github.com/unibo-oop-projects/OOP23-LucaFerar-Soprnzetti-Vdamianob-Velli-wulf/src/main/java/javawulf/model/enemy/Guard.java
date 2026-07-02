package javawulf.model.enemy;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;

/**
 * The guard defends the room he is in and will attack the player
 * if he is in the same room. It guards the items.
 */
public final class Guard extends EnemyImpl {

    private static final int KILLVALUE = 2;
    private static final int STUN_TIME = 5;
    private static final int POINTS = 500;

    private boolean isAlive;

    /**
     * Creates a guard.
     * 
     * @param position the position of the guard when created
     */
    public Guard(final Coordinate position) {
        super(position);
        this.isAlive = true;
    }

    /**
     * @return true if the guard is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @param s the sword that the player is using
     * @return true if the guard can be killed, false otherwise
     */
    public boolean isKillable(final Sword s) {
        return s.getSwordStrength() == KILLVALUE;
    }

    /**
     * Checks if the player is in the room.
     * 
     * @param p the player
     * @return true if the player is in the room, false otherwise
     */
    public boolean checkRoom(final Player p) {
        // TODO implement here Return true if player is in the same room as the guard,
        // probably should be private
        return false;
    }

    @Override
    public void move(final Player p, final Map m) {
        if (this.getBounds().getCollisionType().equals(CollisionType.STUNNED) || !this.checkRoom(p)) {
            this.reduceStun();
        } else {

            final int diffX = p.getPosition().getX() - this.getPosition().getX();
            final int diffY = p.getPosition().getY() - this.getPosition().getY();

            if (!this.isCollidingWithWall(m)) {
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    this.getPosition().setPosition(
                            this.getPosition().getX() + (int) Math.signum(diffX) * this.getSpeed() * MOVEMENT_DELTA,
                            this.getPosition().getY());
                } else {
                    this.getPosition().setPosition(this.getPosition().getX(),
                            this.getPosition().getY() + (int) Math.signum(diffY) * this.getSpeed() * MOVEMENT_DELTA);
                }

                this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), OBJECT_SIZE,
                        OBJECT_SIZE);
            }
        }
    }

    @Override
    public void takeHit(final Player p) {
        if (super.isHit(p.getSword().getBounds())) {
            if (this.isKillable(p.getSword())) {
                this.isAlive = false;
                p.getScore().addPoints(POINTS);
                this.getBounds().changeCollisionType(CollisionType.INACTIVE);
            } else {
                this.setStun(STUN_TIME);
            }
        }
    }
}
