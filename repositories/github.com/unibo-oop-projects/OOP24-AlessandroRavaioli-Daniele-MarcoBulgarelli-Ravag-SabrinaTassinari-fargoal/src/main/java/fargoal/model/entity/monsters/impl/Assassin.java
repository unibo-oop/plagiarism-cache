package fargoal.model.entity.monsters.impl;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.ai.Ai;
import fargoal.model.entity.monsters.api.AbstractMonster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A class that develops the monster Assassin:
 * an enemy who's generally invisible.
 */
public class Assassin extends AbstractMonster {

    private static final int NEXT_MOVE = 1500;
    private static final int MIN_WAIT = 1000;
    private static final int MAX_WAIT = 1450;
    private static final int FLOOR_CHANGE = 13;
    private final int minimumWait;
    private int nextMove;

    /**
     * A constructor for the Assassin, it uses the
     * super of the AbstractMonster constructor but with 
     * his visibility off.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorManager - to get infos about the other entities/items
     * @param renderFactory - to give a render to the Assassin
     */
    public Assassin(final Position position,
            final Integer level, 
            final FloorManager floorManager,
            final RenderFactory renderFactory) {
        super(position, level, floorManager);
        if (floorManager.getFloorLevel() > FLOOR_CHANGE) {
            this.minimumWait = MIN_WAIT;
        } else {
            this.minimumWait = MAX_WAIT;
        }
        this.setMonsterType(MonsterType.ASSASSIN);
        this.setRenderer(renderFactory);
        this.setVisibilityOff();
    }

    private void setRenderer(final RenderFactory renderFactory) {
        this.setRender(renderFactory.assassinRenderer(this));
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "ASSASSIN";
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        final long temp = System.currentTimeMillis();
        if (this.areNeighbours(floorManager, 3)) {
            this.setVisibilityOn();
        } else {
            this.setVisibilityOff();
        }
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
                this.setVisibilityOff();
                Ai.move(this, floorManager.getPlayer(), floorManager);
            }
        }
    }
}
