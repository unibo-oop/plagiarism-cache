package thatlevelagain.character.enemies.collision;

import java.util.List;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Class that manage Collision of the Enemy in all direction.
 * 
 * @author
 *
 */
public abstract class Collision implements CollisionInterface {

    /**
     * The increment of the Y when an enemy touch an obstacle to overpass it. 
     */
    protected static final int DELTA_Y = 10;

    /**
     * fields of the collision. True if the Enemy is colliding
     */
    private boolean collisionTop;
    private boolean collisionBottom;
    private boolean collisionSide;
    private boolean falling;

    /**
     * 
     * @param enemy 
     *          Object of class that implements EnemyInterface
     * @param mattoni 
     *          List of Object of the class Mattoni
     */
    public void checkColliding(final EnemyInterface enemy, final List<Mattoni> mattoni) {
        //verify if there is a collision with one of the Mattoni in all diretions
        for (final Mattoni m : mattoni) {
            if (new StrategySide(enemy).collision(m)) {
                this.collisionSide = true;
                break;
            }
        }
        for (final Mattoni m : mattoni) {
            if (new StrategyTop(enemy).collision(m)) {
                this.collisionTop = true;
                break;
            }
        }
        for (final Mattoni m : mattoni) {
            if (new StrategyBottom(enemy).collision(m)) {
                this.collisionBottom = true;
                break;
            }
        }
    }

    /**
     * 
     * Method that change the behaviour of the Enemy, according to the collision.
     * 
     * @param enemy 
     *          Object of class that implements EnemyInterface
     * @param mattoni
     *          List of Object of the class Mattoni 
     */
    public abstract void intersection(EnemyInterface enemy, List<Mattoni> mattoni);


    @Override
    public final boolean isCollisionTop() {
        return collisionTop;
    }

    @Override
    public final boolean isCollisionBottom() {
        return collisionBottom;
    }

    @Override
    public final boolean isCollisionSide() {
        return collisionSide;
    }

    @Override
    public final void setCollisionTop(final boolean flag) {
        this.collisionTop = flag;
    }

    @Override
    public final void setCollisionBottom(final boolean flag) {
        this.collisionBottom = flag;
    }

    @Override
    public final void setCollisionSide(final boolean flag) {
        this.collisionSide = flag;
    }

    @Override
    public final boolean isFalling() {
        return falling;
    }

    @Override
    public final void setFalling(final boolean flag) {
        falling = flag;
    }

    @Override
    public final void resetColliding() {
        this.collisionBottom = false;
        this.collisionTop = false;
        this.collisionSide = false;
    }

}
