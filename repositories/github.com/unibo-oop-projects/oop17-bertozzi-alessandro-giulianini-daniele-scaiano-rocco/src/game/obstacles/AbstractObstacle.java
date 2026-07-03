package game.obstacles;

import game.Entity;
import game.GameObject;
import game.ID;
import utilities.Pair;

/**
 * Class that models obstacles in the game. Obstacle is an {@link game.Entity} that moves inside the gameArea causing damages to the 
 * {@link game.Player} instance. Unlike {@link game.enemy.InterfaceEnemy}, obstacle is not able to chase the Player neither to shot at him. To check collisions 
 * obstacles are represented over the game area by a square shape (@link ObstacleHitBox}).
 */
public abstract class AbstractObstacle extends Entity implements Obstacle {
    /**
     * Constructor used for the SimpleObstacle to be created.
     * @param position the starting position of the entity
     * @param velocityX the starting x velocity of the entity
     * @param velocityY the starting y velocity of the entity
     * @param sideLength of the side of the hitBox of the obstacle to be created.
     * @param id the id identifying what this entity is
     */
    public AbstractObstacle(final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final int sideLength, final ID id) {     //costruttore per simpleObstacle
        super(position, velocityX, velocityY, id);
        this.setHitbox(new ObstacleHitBox(position, sideLength));
    }
    /** return an {@link AbstractObstacle}.
     * @param id id.
    */
    public AbstractObstacle(final ID id) {      //costruttore per decoratori
        super(id);
    }
    /**
     * Updates the status of the obstacle.
     */
    @Override
    public void update() {
        this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
        ((ObstacleHitBox) (this.getHitbox())).updateHitBox(this.getPosition());
        this.updateEntity();
    }

    /**
     * Updates all the not-physics aspects of the obstacle status.
     */
    protected abstract void updateEntity();

    /**
     * Class that models the simplest version of an obstacle: vulnerable, moving, but not bouncing against the edges and other entities.
     */
    public static class SimpleObstacle extends AbstractObstacle {
        private static final int SIMPLE_OBSTACLE_LIFE = 3;
        private int life = SIMPLE_OBSTACLE_LIFE;
        /**Constructs a {@link SimpleObstacle} with the specified position, velocity and with a square hitBox with a side sideLength long.
         * @param position to place the object.
         * @param velocityX velocityX of the Obstacle instance.
         * @param velocityY velocityY of the Obstacle instance.
         * @param sideLength length of the side of the hitBox of the obstacle to be created.
         */
        public SimpleObstacle(final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final int sideLength) {
            super(position, velocityX, velocityY, sideLength, ID.SMP_OBSTACLE);
        }
        /**
         * @param entity the entity which this obstacle is colliding with.
         */
        @Override
        public void collide(final GameObject entity) { //L'effetto della collisione sul player è gestito nella classe del Player.
            this.life--;
            if (this.life <= 0) {
                this.setDead();
            }
        }
        /**
         * {@inheritDoc}.
         */
        @Override
        protected void updateEntity() {
            if (((ObstacleHitBox) (this.getHitbox())).isOutOfGameArea()) {
                this.setDead();
            }
        }
    }
}
