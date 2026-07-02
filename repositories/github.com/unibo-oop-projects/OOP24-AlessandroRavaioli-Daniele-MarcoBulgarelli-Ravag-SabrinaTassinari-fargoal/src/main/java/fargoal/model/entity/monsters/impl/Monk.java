package fargoal.model.entity.monsters.impl;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.ai.Ai;
import fargoal.model.entity.monsters.api.AbstractMonster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A class that develops the monster Monk:
 * an enemy that bring with him healing potions
 * that make him recovers hp by drinking.
 */
public class Monk extends AbstractMonster {

    private static final int NEXT_MOVE = 2000;
    private static final int HEAL_CONSTANT = 19;
    private static final int MIN_WAIT = 1500;
    private static final int MAX_WAIT = 1650;
    private static final int FLOOR_CHANGE = 6;
    private final int minimumWait;
    private int nextMove;

    /**
     * A constructor for the Monk; it uses the
     * super of the AbstractMonster constructor.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorManager - to get infos about the other entities/items
     * @param renderFactory - to give a render to the Monk
     */
    public Monk(final Position position, 
            final Integer level,
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        super(position, level, floorManager);
        if (floorManager.getFloorLevel() > FLOOR_CHANGE) {
            minimumWait = MIN_WAIT;
        } else {
            minimumWait = MAX_WAIT;
        }
        setMonsterType(MonsterType.MONK);
        this.setRenderer(renderFactory);
    }

    private void setRenderer(final RenderFactory renderFactory) {
        this.setRender(renderFactory.monkRenderer(this));
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "MONK";
    }

    /**
     * The Monster heal himself with a HealingPotion
     * to recover hp.
     */
    private void heal() {
        this.increaseHealth(this.getRandom(HEAL_CONSTANT) + 3 * this.getLevel());
        if (this.getCurrentHealth() > this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
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
            } else if (!this.isHealthy() && !this.areNeighbours(floorManager, 2)) {
                this.heal();
            } else {
                Ai.move(this, floorManager.getPlayer(), floorManager);
            }
        }
    }
}
