package game.obstacles;

import java.awt.Rectangle;

import game.GameImpl;
import game.GameObject;
import game.ID;
import game.Player;
import utilities.Pair;

/**
 * Abstract root class common to all {@link AbstractObstacle}-decorators instances.
 */
public abstract class ObstacleDecorator extends AbstractObstacle {
    private final AbstractObstacle decorated;
    /** Constructor used by all {@link ObstacleDecorator} subclasses. Subclasses allow to create a hierarchy of {@link AbstractObstacle}s
     * with different behaviors by simply composing subclasses instances each responsible of one specific feature.
     * @param obstacle a obstacle. 
     * @param id id*/
    public ObstacleDecorator(final AbstractObstacle obstacle, final ID id) {
        super(id);
        this.decorated = obstacle;
    }
    @Override
    public abstract void collide(GameObject entity);

    /**{@link game.Entity#getPosition()}. */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.decorated.getPosition();
    }
    /**{@link game.Entity#setVelocity(int velocityX, int velocityY)}. */
    @Override
    public void setVelocity(final int velocityX, final int velocityY) {
        this.decorated.setVelocity(velocityX, velocityY);
    }
    /**{@link game.Entity#getVelocity()}. */
    @Override
    public Pair<Integer, Integer> getVelocity() {
        return this.decorated.getVelocity();
    }
    /**{@link game.Entity#setHitbox(Rectangle hb)}. */
    @Override
    public void setHitbox(final Rectangle hb) {
        this.decorated.setHitbox(hb);
    }
    /**{@link game.Entity#getHitbox()}. */
    @Override
    public Rectangle getHitbox() {
        return this.decorated.getHitbox();
    }
    /**{@link game.Entity#isDead()}. */
    @Override
    public boolean isDead() {
        return this.decorated.isDead();
    }
    @Override
    /**{@link game.Entity#setDead()}. */
    public void setDead() {
        this.decorated.setDead();
    }
    /**{@link AbstractObstacle#updateEntity()}. */
    @Override
    protected void updateEntity() {
        this.decorated.updateEntity();
    }
    /** @return encapsulated obstacle.*/
    protected AbstractObstacle getDecorated() {
        return this.decorated;
    }

    /**
     * Obstacle able to bounce against the player and the edges of the game area.
     */
    public static final class BouncingObstacle extends ObstacleDecorator {
        private static final int MAX_VELOCITY = 10;
        /**
         * creates a {@link BouncingObstacle} instance.
         * @param obstacle an obstacle
         */
        public BouncingObstacle(final AbstractObstacle obstacle) {
            super(obstacle, ID.BNC_OBSTACLE);
        }
        @Override
        public void collide(final GameObject entity) {
            this.bounceOnThePlayer(entity);
            this.getDecorated().collide(entity);
        }
        @Override
        protected void updateEntity() {
            this.getDecorated().updateEntity();
            this.bounceOnBoundaries();
        }
        private void bounceOnThePlayer(final GameObject entity) {
            if (entity instanceof Player) {
                this.setVelocity((-this.getVelocity().getX() + 2 * entity.getVelocity().getX()) % MAX_VELOCITY,
                        (-this.getVelocity().getY() + 2 * entity.getVelocity().getY()) % MAX_VELOCITY); //supposto massa ostacolo irrilevante rispetto a massa player (total-elastic rebound)
            }
        }
        private void bounceOnBoundaries() {
            if (this.getPosition().getX() + Math.floor(this.getHitbox().getWidth() / 2) > GameImpl.GAMEAREA_WIDTH
                    || this.getPosition().getX() - Math.ceil(this.getHitbox().getWidth() / 2) < 0) {
                this.setVelocity(-this.getVelocity().getX(), this.getVelocity().getY());        //flipX
            }
            if (this.getPosition().getY() + Math.floor(this.getHitbox().getHeight() / 2) > GameImpl.GAMEAREA_HEIGHT
                    || this.getPosition().getY() - Math.ceil(this.getHitbox().getHeight() / 2) < 0) {
                this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY());       //flipY
            }
        }
    }

    /**
     * Obstacle that gets bigger and bigger (inflating obstacle) as long as time passes.
     */
    public static final class EnlargingObstacle extends ObstacleDecorator {
        private static final int TIME_RATE = 10;
        private int timer;
        /**
         * creates a {@link ObstacleDecorator.EnlargingObstacle} instance.
         * @param obstacle a obstacle
         */
        public EnlargingObstacle(final AbstractObstacle obstacle) {
            super(obstacle, obstacle instanceof SimpleObstacle ? ID.ENL_OBSTACLE : ID.ENL_BNC_OBSTACLE);
            timer = 0;
        }
        @Override
        protected void updateEntity() {
            this.getDecorated().updateEntity();
            this.enlarge();
        }
        @Override
        public void collide(final GameObject entity) {
            this.getDecorated().collide(entity);
        }
        private void enlarge() {
            timer++;
            if (timer % TIME_RATE == 0) {
                this.setHitbox(new ObstacleHitBox(this.getPosition(), (int) this.getHitbox().getHeight() + 1)); 
            }
        }
    }

    /**
     * Class that model obstacles with a limited range of time before being removed from the game area (dying).
     */
    public static final class TimeLimitedObstacle extends ObstacleDecorator {
        private static final int TIME_LIFE = 500;
        private int timer;
        /**
         * creates a {@link TimeLimitedObstacle} instance.
         * @param obstacle obstacle
         */
        public TimeLimitedObstacle(final AbstractObstacle obstacle) {
            super(obstacle, 
                    obstacle instanceof SimpleObstacle ? ID.TML_OBSTACLE
                            :  obstacle instanceof BouncingObstacle ? ID.TML_BNC_OBSTACLE
                                    : ((ObstacleDecorator) obstacle).getDecorated() instanceof BouncingObstacle ? ID.TML_ENL_BNC_OBSTACLE : ID.TML_ENL_OBSTACLE);
            this.timer = TIME_LIFE;
        }
        @Override
        public void collide(final GameObject entity) {
            this.getDecorated().collide(entity);
        }
        @Override
        protected void updateEntity() {
            this.getDecorated().updateEntity();
            this.decreaseTimeLife();
        }
        private void decreaseTimeLife() {
            timer--;
            if (timer <= 0) {
                this.setDead();
            }
        }
    }

}
