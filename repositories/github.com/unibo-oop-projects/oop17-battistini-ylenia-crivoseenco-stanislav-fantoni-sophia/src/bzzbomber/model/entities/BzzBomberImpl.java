package bzzbomber.model.entities;

import java.awt.Point;
import java.util.Set;

import bzzbomber.controller.collision.BomberCollision;
import bzzbomber.controller.collision.BomberCollisionImpl;
import bzzbomber.model.Model;
import bzzbomber.model.ResetType;
import bzzbomber.model.utilities.Direction;
import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Implementation of @BzzBomber .
 * 
 */
public final class BzzBomberImpl extends LivingCreatureImpl implements BzzBomber {
    /**
     * Number of initial life.
     */
    public static final int INITIAL_LIFE = 3;
    /**
     * Number of initial monster that hero killed.
     */
    public static final int INITIAL_MONSTER = 0;
    private static final int INITIAL_BOMB = 1;
    private static final int INITIAL_TIMEOUT = 0;
    private static final int FINAL_TIMEOUT = 2;
    private static final int MOLTIPLY_LIFE = 10;
    private static final int MOLTIPLY_TIME = 5;

    private final BomberCollision heroCollision;
    private final Model model;
    private int timeoutCollision;
    private int numberBomb;
    private int enemyKilled;
    private TileImpl tile;
    private final TileImpl tileHero = new TileImpl(TileImg.BOMBER);
    private final TileImpl tileImmunity = new TileImpl(TileImg.IMMUNITY);

    /**
     * Constructor for @Hero .
     * 
     * @param pos
     *            The initial position of Hero
     * @param model
     *            The model
     */
    public BzzBomberImpl(final Point pos, final Model model) {
        super(pos, INITIAL_LIFE);
        this.model = model;
        this.heroCollision = new BomberCollisionImpl(this);
        this.enemyKilled = INITIAL_MONSTER;
        this.timeoutCollision = INITIAL_TIMEOUT;
        this.numberBomb = INITIAL_BOMB;
        this.tile = new TileImpl(TileImg.BOMBER);
    }

    @Override
    public void move(final Direction dir, final Set<Block> blockSet, final Set<Bomb> bombSet,
            final Set<Insects> insectSet) {
        this.heroCollision.updateEntityHitbox(dir);
        if (!this.heroCollision.blockCollision(blockSet) && !this.heroCollision.insectsCollision(insectSet)
                && !this.model.getCurrentLevel().intersectsWithWall(super.getCollisionBox())) {
            super.move(dir);
        } else {
            if (this.timeoutCollision == INITIAL_TIMEOUT && this.heroCollision.insectsCollision(insectSet)) {
                this.removeLife();
                this.timeoutCollision = FINAL_TIMEOUT;
                System.out.println("Rimuovi Vita");
                this.model.changeLife();
            }
            this.heroCollision.updateEntityHitbox(dir.getOppositeDirection());
        }
    }

    @Override
    public void decrementImmunity() {
        if (this.timeoutCollision > INITIAL_TIMEOUT) {
            this.tile = this.tileImmunity;
            this.timeoutCollision--;
        } else {
            this.tile = this.tileHero;
        }
    }

    @Override
    public void contactWithEntity() {
        if (this.heroCollision.explosionCollision(this.model.getAllExplosions())) {
            this.removeLife();
            this.model.changeLife();
        }
        if (this.heroCollision.openDoorCollision(this.model.getDoor())) {
            this.model.nextLevel();
        }
        if (this.heroCollision.healthCollision(this.model.getAllHeart()) && super.getRemainingLives() < INITIAL_LIFE) {
            super.addLife();
            this.model.changeLife();
        }
    }

    @Override
    public void plantBomb(final Set<Bomb> bombSet) {
        if (!this.heroCollision.bombCollision(bombSet) && this.numberBomb > 0) {
            this.numberBomb--;
            this.model.getCurrentLevel().getEntityManager().addEntity(new Bomb(this.getPosition()));
        }

    }

    @Override
    public BomberCollision getHeroCollision() {
        return this.heroCollision;
    }

    @Override
    public int getEnemyKilled() {
        return this.enemyKilled;
    }

    @Override
    public void addEnemyKilled() {
        this.enemyKilled++;
        this.model.changeEnemy();
    }

    @Override
    public int calculateScore() {
        return this.getRemainingLives() * BzzBomberImpl.MOLTIPLY_LIFE
                + this.model.getTimer().getSecond() * BzzBomberImpl.MOLTIPLY_TIME;
    }

    @Override
    public void setNumberBomb() {
        this.numberBomb = INITIAL_BOMB;
    }

    @Override
    public TileImpl getTile() {
        return this.tile;
    }

    @Override
    public void reset(final ResetType rt) {
        if (rt.equals(ResetType.TOTAL)) {
            super.setLife(BzzBomberImpl.INITIAL_LIFE);
            this.model.changeLife();
        }
        this.enemyKilled = BzzBomberImpl.INITIAL_MONSTER;
        this.model.changeEnemy();
        this.numberBomb = BzzBomberImpl.INITIAL_BOMB;
    }

}
