package model;

import java.awt.Point;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * An entity subclass that defines and contains the characteristics of a fighter
 *
 */
public class Fighter extends Entity {
    private Direction currentDirection;
    private Status currentStatus;
    private float damage;
    private final Timer.Task resetStatusHit;
    private final String folderName;
    private final String namePlayer;

    /**
     * Every time a Fighter is created, a Task that resets the Status to IDLE is also created
     * and it's called whenever the fighter is hit
     * @param point
     * 				The Fighter's original point
     * @param width
     * 				The Fighter's width
     * @param height
     * 				The Fighter's height
     * @param type
     * 				The Fighter's BodyType
     * @param world
     * 				The world in which to create the Fighter
     * @param initialDirection
     *              The Fighter's initial direction
     * @param nameFolder
     *              The Fighter's folder
     * @param namePlayer
     *              The Fighter's name
     */
    public Fighter(final Point point, final float width, final float height, final BodyType type,
                   final World world, final Direction initialDirection, final String nameFolder, final String namePlayer) {
        super(new Vector2(point.x, point.y), width, height, type, world);
        this.currentStatus = Status.IDLE;
        this.currentDirection = initialDirection;
        this.getBody().setUserData(this);
        this.folderName = nameFolder;
        this.namePlayer = namePlayer;

        resetStatusHit = new Task() {
            @Override
            public void run() {
                setCurrentStatus(Status.IDLE);
                getBody().setLinearVelocity(0, 0);
            }
        };
    }

    /**
     * @return the current status of the fighter
     */
    public Status getCurrentStatus() {
        return this.currentStatus;
    }

    /**
     * Sets the currentStatus of the player using the given parameter
     * @param currentStatus
     */
    public void setCurrentStatus(final Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return the current direction of the fighter
     */
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * Sets the currentDirection of the player using the given parameter
     * @param currentDirection
     */
    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * The Fighter recognizes that he was attacked, sets his Status to HIT,
     * updates his damage and calculates his knockback
     * @param damage
     *              The damage dealt to the fighter by the incoming attack
     * @param direction
     *              The direction of the incoming attack
     */
    public void setHit(final float damage, final Direction direction) {
        this.damage = this.damage + damage;
        this.damage = 0.3f + this.damage / 100;
        if (this.resetStatusHit.isScheduled()) {
            this.resetStatusHit.cancel();
        }
        this.currentStatus = Status.HIT;
        Timer.schedule(resetStatusHit, this.damage);
        this.getBody().setLinearVelocity(direction.equals(Direction.RIGHT) ? 25 + damage : - (25 + damage), 60f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOut(final OrthographicCamera camera) {
        float currentX = this.getBody().getPosition().x - ((camera.viewportWidth * (1 - camera.zoom)) / 2);
        float currentY = this.getBody().getPosition().y - ((camera.viewportHeight * (1 - camera.zoom)) / 2);
        float effettiveWidth = (camera.viewportWidth * camera.zoom);
        float effettiveHeight = (camera.viewportHeight * camera.zoom);
        if (currentX > 0 && currentX < effettiveWidth && currentY > 0 && currentY < effettiveHeight) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return the Fighter's folder name
     */
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * @return the Fighter's name given by player
     */
    public String getNamePlayer() {
        return this.namePlayer;
    }
}
