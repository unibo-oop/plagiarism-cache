package model;

import java.awt.Graphics2D;

import input.EnemyInputComponent;
import input.IaBalloomComponent;
import utilities.Position;

/**
 * This class models a BalloomEnemy.
 * First type of monster, BalloomEnemy's moves are random and it collides with every block,
 * except with other enemies.
 */

public class BalloomEnemy extends AbstractEntity {

    private static final int MILLISECONDS_BETWEEN_COMMAND = 1000;
    private final EnemyInputComponent iaInputComponent;
    private long timer;

    /**
    * Creates Balloom enemy.
    * @param position : balloom enemy initial position.
    * @param solid : says if balloom is solid or not.
    * @param breakable : says if balloom is breakable or not.
    */
    public BalloomEnemy(final Position position, final boolean solid, final boolean breakable) {
        super(position, solid, breakable);
        this.iaInputComponent = new IaBalloomComponent(this);
        this.timer = System.currentTimeMillis();
    }

    /**
    * {@inheritDoc}
    **/
    @Override
    public void update(final long elapsedTime) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        this.clockCommand();
        if (!super.getCommandQueue().isEmpty()) {
            super.getCommandQueue().remove(0).execute();
        }
    }

    private void clockCommand() {
        if (this.enemyTimerClock(this.timer)) {
            this.iaInputComponent.generateCommand();
            this.timer = System.currentTimeMillis();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        // TODO Auto-generated method stub

    }

    private boolean enemyTimerClock(final long time) {
        return System.currentTimeMillis() - time >= MILLISECONDS_BETWEEN_COMMAND;
    }
}
