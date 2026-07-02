package bzzbomber.model.entities;

import java.awt.Point;
import java.util.Random;
import java.util.Set;
import bzzbomber.controller.collision.InsectCollision;
import bzzbomber.controller.collision.InsectCollisionImpl;
import bzzbomber.model.Model;
import bzzbomber.model.utilities.Direction;
import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * 
 * This class implements Insects extends @LivingCreatureImpl and
 * implements @Insects.
 *
 */

public final class InsectsImpl extends LivingCreatureImpl implements Insects {

    /**
     * This constant is monster's initial life.
     */
    public static final int INITIAL_LIFE = 1;
    private final InsectCollision insectCollision;
    private boolean check;
    private Direction currentDirection;
    private final Model model;
    private final TileImpl tileInsectDOWN = new TileImpl(TileImg.INSECT_DOWN);
    private final TileImpl tileInsectUP = new TileImpl(TileImg.INSECT_UP);
    private final TileImpl tileInsectLEFT = new TileImpl(TileImg.INSECT_LEFT);
    private final TileImpl tileInsectRIGHT = new TileImpl(TileImg.INSECT_RIGHT);

    /**
     * Constructor of InsectsImpl.
     * 
     * @param position
     *            The initial position.
     * @param model
     *            The model.
     */

    public InsectsImpl(final Point position, final Model model) {
        super(position, INITIAL_LIFE);
        this.insectCollision = new InsectCollisionImpl(this);
        this.currentDirection = getRandomDirection();
        this.model = model;
        this.check = true;
    }

    @Override
    public Direction getRandomDirection() {
        final Direction[] vet = Direction.values();
        return vet[new Random().nextInt(vet.length)];
    }

    /**
     * This method checks if the insects collides with blocks or with the hero or
     * with planted bombs.
     * 
     * @param blockSet
     *            the set of blocks that are in the map
     * @param hero
     *            the hero's entity
     * @param bombSet
     *            the set of planted bomb
     * 
     * @return TRUE if it collides, FALSE otherwise
     */

    private boolean checkCollision(final Set<Block> blockSet, final Set<Bomb> bombSet, final BzzBomber hero) {
        return this.insectCollision.blockCollision(blockSet) || this.insectCollision.bombCollision(bombSet)
                || this.insectCollision.heroCollision(hero)
                || this.model.getCurrentLevel().intersectsWithWall(super.getCollisionBox()) ? true : false;
    }

    /**
     * This method control if the insects can move. When the insects collided with
     * something this method search another direction. This method also control
     * WallCollision and ExplosionCollision. If the insects doesn't collided 'll
     * called method move.
     * 
     * @param blockSet
     *            are all block in the map
     * @param bombSet
     *            are all bomb in the map
     * @param hero
     *            is the hero Entity
     */

    private void possibleMove(final Set<Block> blockSet, final Set<Bomb> bombSet, final BzzBomber hero) {
        this.insectCollision.updateEntityHitbox(this.currentDirection);
        this.check = checkCollision(blockSet, bombSet, hero);
        while (this.check) {

            this.insectCollision.updateEntityHitbox(this.currentDirection.getOppositeDirection());
            this.currentDirection = this.getRandomDirection();
            this.insectCollision.updateEntityHitbox(this.currentDirection);

            this.check = checkCollision(blockSet, bombSet, hero);

        }

        if (this.insectCollision.explosionCollision(this.model.getAllExplosions())) {
            super.removeLife();
            System.out.println("SONO MORTO");
        }
        super.move(this.currentDirection);
    }

    @Override
    public InsectCollision getEnemyCollision() {
        return this.insectCollision;
    }

    @Override
    public void move(final Set<Block> blockSet, final Set<Bomb> bombSet, final BzzBomber hero) {
        this.possibleMove(blockSet, bombSet, hero);
    }

    @Override
    public TileImpl getTile() {

        if (this.currentDirection == Direction.UP) {
            return this.tileInsectUP;
        } else {
            if (this.currentDirection == Direction.DOWN) {
                return this.tileInsectDOWN;
            } else {
                if (this.currentDirection == Direction.RIGHT) {
                    return this.tileInsectRIGHT;
                } else {
                    return this.tileInsectLEFT;
                }
            }
        }
    }

}
