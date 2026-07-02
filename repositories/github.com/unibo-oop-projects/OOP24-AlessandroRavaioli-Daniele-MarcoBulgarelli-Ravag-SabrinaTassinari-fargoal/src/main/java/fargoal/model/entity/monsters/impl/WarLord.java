package fargoal.model.entity.monsters.impl;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.ai.Ai;
import fargoal.model.entity.monsters.api.AbstractMonster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A class that develops the monster WarLord:
 * an enemy that often carries a War Shield to protect
 * himself from the attacks of the player.
 */
public class WarLord extends AbstractMonster {

    private static final int NEXT_MOVE = 1500;
    private static final int MIN_WAIT = 1000;
    private static final int MAX_WAIT = 1500;
    private static final int FLOOR_CHANGE = 12;
    private final int minimumWait;
    private final int numForShield;
    private boolean shield;
    private int nextMove;

    /**
     * A constructor for the War Lord; it uses the
     * super of the AbstractMonster constructor and he's
     * created with a built-in shield.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorManager - to get infos about the other entities/items
     * @param renderFactory - to give a render to the War Lord
     */
    public WarLord(final Position position, 
            final Integer level,
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        super(position, level, floorManager);
        setMonsterType(MonsterType.WAR_LORD);
        numForShield = this.getRandom(3);
        if (floorManager.getFloorLevel() > FLOOR_CHANGE) {
            minimumWait = MIN_WAIT;
        } else {
            minimumWait = MAX_WAIT;
        }
        if (numForShield == 0 || numForShield == 1) {
            this.shield = true;
        } else {
            this.shield = false;
        }
        this.setRenderer(renderFactory);
    }

    private void setRenderer(final RenderFactory renderFactory) {
        this.setRender(renderFactory.warlordRenderer(this));
    }

    /** {@inheritDoc} */
    @Override
    public void receiveDamage() {
        final int damage = this.getFloorManager().getPlayer().doDamage(this);
        if (shield) {
            shield = false;
        } else {
            this.decreaseHealth(damage);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "WAR_LORD";
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        final long temp = System.currentTimeMillis();
        if (Math.abs(this.getTimer() - temp) >= nextMove) {
            this.nextMove = this.getRandom(NEXT_MOVE * this.getSkill() / this.getLevel()) + minimumWait;
            this.setTimer();
            if (this.areNeighbours(floorManager, 1) 
                    && !floorManager.getPlayer().isImmune()
                    && floorManager.getPlayer().isVisible()) {
                floorManager.getPlayer().setIsAttacked(true);
                this.setIsFighting(true);
                floorManager.getPlayer().battle(this);
            } else {
                Ai.move(this, floorManager.getPlayer(), floorManager);
            }
        }
    }
}
